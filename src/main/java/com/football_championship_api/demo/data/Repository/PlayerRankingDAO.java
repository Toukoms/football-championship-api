package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.Entity.Championship;
import com.football_championship_api.demo.data.Entity.DurationUnit;
import com.football_championship_api.demo.data.Entity.PlayerRanking;
import com.football_championship_api.demo.data.Entity.PlayingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class PlayerRankingDAO implements GeneralDAO<PlayerRanking> {

    private final CustomDataSource dataSource;

    @Autowired
    public PlayerRankingDAO(CustomDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    @Override
    public void insert(PlayerRanking ranking) {
        String sql = "INSERT INTO player_ranking (player_id, scored_goals, playing_time_value, playing_time_unit) " +
                "VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (player_id) DO UPDATE SET " +
                "scored_goals = EXCLUDED.scored_goals, " +
                "playing_time_value = EXCLUDED.playing_time_value, " +
                "playing_time_unit = EXCLUDED.playing_time_unit;";


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, ranking.getPlayer());
            stmt.setInt(2, ranking.getScoredGoals());

            PlayingTime time = ranking.getPlayingTime();
            time.convertToSeconds();

            stmt.setObject(3, time.getValue());
            stmt.setString(4, time.getDurationUnit().name());


            stmt.executeUpdate();

            System.out.println("Player inserted : " + ranking.getPlayer());

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting player ranking", e);
        }
    }


    public List<PlayerRanking> findBestPlayers(Integer top, String playingTimeUnit) {

        List<PlayerRanking> ranking = new ArrayList<>();
        String sql = "SELECT player_id,scored_goals, playing_time_value, playing_time_unit FROM player_ranking ORDER BY scored_goals DESC, playing_time_value DESC LIMIT ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, top);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayerRanking pr = new PlayerRanking();
                pr.setPlayer((UUID) rs.getObject("player_id"));
                pr.setScoredGoals(rs.getInt("scored_goals"));

                PlayingTime time = new PlayingTime();
                time.setValue(rs.getInt("playing_time_value"));
                time.setDurationUnit(DurationUnit.valueOf(rs.getString("playing_time_unit")));


                if (playingTimeUnit != null) {
                    switch (playingTimeUnit.toUpperCase()) {
                        case "SECOND":
                            time.convertToSeconds();
                            break;
                        case "MINUTE":
                            time.convertToMinute();
                            break;
                        case "HOUR":
                            time.convertToHour();
                            break;
                        default:
                            throw new IllegalArgumentException("Unrecognized playing time unit: " + playingTimeUnit);
                    }
                }

                pr.setPlayingTime(time);

                ranking.add(pr);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving best players", e);
        }

        return ranking;
    }



}
