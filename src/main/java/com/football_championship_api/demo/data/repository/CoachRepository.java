package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.CoachEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CoachRepository {
    private final CustomDataSource dataSource;

    public CoachEntity save(CoachEntity coach) {
        String sql = "INSERT INTO coach (id, name, nationality, created_at) VALUES (?,?,?,?) " +
                "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, nationality = EXCLUDED.nationality, created_at = EXCLUDED.created_at RETURNING id";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, coach.getId() != null ? coach.getId() : UUID.randomUUID());
            stmt.setString(2, coach.getName());
            stmt.setString(3, coach.getNationality());
            stmt.setInt(4, coach.getCreatedAt());
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return findById((UUID) rs.getObject("id"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving coach : " + sql, e);
        }
    }

    public CoachEntity findById(UUID coachId) {
        String sql = "SELECT * FROM coach WHERE id = ?";
        try (java.sql.Connection conn = dataSource.getConnection();java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, coachId);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCoach(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding coach by id", e);
        }
    }

    private CoachEntity mapResultSetToCoach(java.sql.ResultSet rs) throws java.sql.SQLException {
        CoachEntity coach = new CoachEntity();
        coach.setId((UUID) rs.getObject("id"));
        coach.setName(rs.getString("name"));
        coach.setNationality(rs.getString("nationality"));
        coach.setCreatedAt(rs.getInt("created_at"));
        return coach;
    }
}
