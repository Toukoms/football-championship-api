package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.PlayerStatisticsEntity;
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

    public PlayerStatisticsEntity getStatisticsOfPlayerById(UUID playerId, Integer seasonYear) {
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

    private PlayerStatisticsEntity mapResultSetToPlayerStatistics(java.sql.ResultSet rs) throws java.sql.SQLException {
        PlayerStatisticsEntity playerStatisticsEntity = new PlayerStatisticsEntity();
        playerStatisticsEntity.setScoredGoals(rs.getLong("scored_goals"));
        playerStatisticsEntity.setPlayingTimeValue(rs.getLong("playing_time_value"));
        playerStatisticsEntity.setPlayingTimeUnit(PlayingTimeUnit.valueOf(rs.getString("playing_time_unit")));
        playerStatisticsEntity.setPlayer(playerRepository.findById((UUID) rs.getObject("player_id")));
        playerStatisticsEntity.setSeason(seasonRepository.findById((UUID) rs.getObject("season_id")));

        return playerStatisticsEntity;
    }
}
