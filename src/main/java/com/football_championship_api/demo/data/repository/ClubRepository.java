package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.ClubEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClubRepository {

    private final CustomDataSource dataSource;
    private final CoachRepository coachRepository;

    public ClubEntity findById(UUID clubId) {
        String sql = "SELECT * FROM club WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, clubId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToClub(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding club by id", e);
        }
    }

    private ClubEntity mapResultSetToClub(ResultSet rs) throws SQLException {
        ClubEntity club = new ClubEntity();
        club.setId((UUID) rs.getObject("id"));
        club.setName(rs.getString("name"));
        club.setAcronym(rs.getString("acronym"));
        club.setStadium(rs.getString("stadium"));
        club.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        club.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        club.setCoach(coachRepository.findById((UUID) rs.getObject("coach_id")));
        return club;
    }
}
