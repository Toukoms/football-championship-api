package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.PlayerRanking;
import com.football_championship_api.demo.data.entity.PlayingTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "INSERT INTO player_ranking (" +
                "player_id, championship, scored_goals, playing_time_value, playing_time_unit) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, ranking.getPlayer());
            stmt.setString(2, ranking.getChampionship().name());
            stmt.setInt(3, ranking.getScoredGoals());

            PlayingTime time = ranking.getPlayingTime();
            stmt.setObject(4, time.getValue());
            stmt.setString(5, time.getDurationUnit().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting player ranking", e);
        }
    }
}
