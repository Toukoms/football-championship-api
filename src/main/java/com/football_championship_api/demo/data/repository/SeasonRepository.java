package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.PlayingStatus;
import com.football_championship_api.demo.data.entity.SeasonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SeasonRepository {

    private final CustomDataSource dataSource;

    public SeasonEntity findById(UUID seasonId) {
        String sql = "SELECT * FROM season WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement
                stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, seasonId);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSeason(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding season by id", e);
        }
        return null;
    }

    public List<SeasonEntity> findAll() {
        String sql = "SELECT * FROM season ORDER BY year DESC";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            java.sql.ResultSet rs = stmt.executeQuery();
            List<SeasonEntity> seasons = new ArrayList<>();
            while (rs.next()) {
                seasons.add(mapResultSetToSeason(rs));
            }
            return seasons;
        } catch (Exception e) {
            throw new RuntimeException("Error finding all seasons", e);
        }
    }

    private SeasonEntity mapResultSetToSeason(java.sql.ResultSet rs) throws java.sql.SQLException {
        SeasonEntity season = new SeasonEntity();
        season.setId((UUID) rs.getObject("id"));
        season.setYear(rs.getInt("year"));
        season.setAlias(rs.getString("alias"));
        season.setStatus(PlayingStatus.valueOf(rs.getString("status")));
        season.setCreatedAt(rs.getInt("created_at"));
        return season;
    }
}
