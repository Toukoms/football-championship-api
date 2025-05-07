package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.ClubEntity;
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
public class ClubRepository {

    private final CustomDataSource dataSource;
    private final CoachRepository coachRepository;

    public ClubEntity save(ClubEntity club) {
        String sql = "INSERT INTO club (id, name, acronym, stadium, coach_id) VALUES (?,?,?,?,?) " +
                "ON CONFLICT (name) DO UPDATE SET acronym = EXCLUDED.acronym, stadium = EXCLUDED.stadium, coach_id = EXCLUDED.coach_id, updated_at = NOW() RETURNING id";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            UUID id = UUID.randomUUID();
            stmt.setObject(1, id);
            stmt.setString(2, club.getName());
            stmt.setString(3, club.getAcronym());
            stmt.setString(4, club.getStadium());
            stmt.setObject(5, club.getCoach() == null ? null : club.getCoach().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return findById((UUID) rs.getObject("id"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving club : " + sql, e);
        }
    }

    public List<ClubEntity> saveAll(List<ClubEntity> clubs) {
        List<ClubEntity> savedClubs = new ArrayList<>();
        for (ClubEntity club : clubs) {
            savedClubs.add(save(club));
        }
        return savedClubs;
    }

    public List<ClubEntity> findAll() {
        String sql = "SELECT * FROM club ORDER BY name ASC";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<ClubEntity> clubs = new ArrayList<>();
            while (rs.next()) {
                clubs.add(mapResultSetToClub(rs));
            }
            return clubs;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all clubs", e);
        }
    }

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
        club.setCreatedAt(rs.getInt("created_at"));
        club.setCoach(coachRepository.findById((UUID) rs.getObject("coach_id")));
        return club;
    }
}
