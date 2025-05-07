package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.PlayingStatus;
import com.football_championship_api.demo.data.entity.SeasonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SeasonRepository {

    private final CustomDataSource dataSource;

    public SeasonEntity updateStatus(Integer seasonYear) {
        SeasonEntity season = findByYear(seasonYear);
        PlayingStatus nextStatus = getNextStatus(seasonYear, season);

        String sql = "UPDATE season SET status = ? WHERE year = ? RETURNING id";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nextStatus.toString());
            stmt.setInt(2, seasonYear);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return findById((UUID) rs.getObject("id"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update season status : " + sql, e);
        }
    }

    private PlayingStatus getNextStatus(Integer seasonYear, SeasonEntity season) {
        if (season == null) {
            throw new IllegalArgumentException("Season with the year=" + seasonYear + " does not exist");
        }
        PlayingStatus currentStatus = season.getStatus();
        PlayingStatus nextStatus = null;

        switch (currentStatus) {
            case NOT_STARTED -> nextStatus = PlayingStatus.STARTED;
            case STARTED -> nextStatus = PlayingStatus.FINISHED;
        }
        assert nextStatus != null;
        return nextStatus;
    }

    public SeasonEntity findByYear(Integer seasonYear) {
        String sql = "SELECT * FROM season WHERE year = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, seasonYear);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSeason(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error find season by year", e);
        }
    }

    public List<SeasonEntity> saveAll(List<SeasonEntity> seasons) {
        List<SeasonEntity> savedSeasons = new ArrayList<>();
        for (SeasonEntity season : seasons) {
            savedSeasons.add(save(season));
        }
        return savedSeasons;
    }

    public SeasonEntity save(SeasonEntity season) {
        String sql = "INSERT INTO season (id, year, alias) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            UUID id = UUID.randomUUID();
            stmt.setObject(1, id);
            stmt.setInt(2, season.getYear());
            stmt.setString(3, season.getAlias());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return findById((UUID) rs.getObject("id"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save season : " + sql, e);
        }
    }

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
        season.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        season.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return season;
    }
}
