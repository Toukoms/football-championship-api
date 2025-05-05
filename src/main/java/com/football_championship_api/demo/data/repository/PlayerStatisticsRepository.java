package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.PlayerStatistics;
import com.football_championship_api.demo.data.entity.PlayingTimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlayerStatisticsRepository {

    private final CustomDataSource dataSource;
    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;

    public PlayerStatistics getStatisticsOfPlayerById(UUID playerId, Integer seasonYear) {
        String sql = "SELECT ps.*  FROM player_statistics ps JOIN season s ON ps.season_id = s.id WHERE ps.player_id = ? AND s.year = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, playerId);
            stmt.setInt(2, seasonYear);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPlayerStatistics(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding statistics of player by id", e);
        }
    }

    private PlayerStatistics mapResultSetToPlayerStatistics(java.sql.ResultSet rs) throws java.sql.SQLException {
        PlayerStatistics playerStatistics = new PlayerStatistics();
        playerStatistics.setScoredGoals(rs.getLong("scored_goals"));
        playerStatistics.setPlayingTimeValue(rs.getLong("playing_time_value"));
        playerStatistics.setPlayingTimeUnit(PlayingTimeUnit.valueOf(rs.getString("playing_time_unit")));
        playerStatistics.setPlayer(playerRepository.findById((UUID) rs.getObject("player_id")));
        playerStatistics.setSeason(seasonRepository.findById((UUID) rs.getObject("season_id")));

        return playerStatistics;
    }
}
