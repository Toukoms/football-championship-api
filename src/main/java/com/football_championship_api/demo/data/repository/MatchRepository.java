package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.ClubEntity;
import com.football_championship_api.demo.data.entity.MatchEntity;
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
public class MatchRepository {
    private final CustomDataSource dataSource;
    private final ClubRepository clubRepository;

    public MatchEntity save(MatchEntity match) {
        String sql = "INSERT INTO match (id, stadium, club_home_id, club_away_id) VALUES (?,?,?,?) RETURNING id";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, UUID.randomUUID());
            stmt.setString(2, match.getStadium());
            stmt.setObject(3, match.getHomeClub().getId());
            stmt.setObject(4, match.getAwayClub().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return findById((UUID) rs.getObject("id"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving match : " + sql, e);
        }
    }

    public List<MatchEntity> findAll(Integer seasonYear, FilterMatch filter) {
        StringBuilder sql = new StringBuilder("SELECT * FROM \"match\" m JOIN club c ON m.club_home_id = c.id JOIN club c2 ON m.club_away_id = c2.id JOIN season s ON m.season_id = s.id WHERE s.year = ?");
        List<Object> params = new ArrayList<>();
        if (filter != null) {
            if (filter.getMatchStatus() != null) {
                sql.append(" AND m.status = ?");
                params.add(filter.getMatchStatus().toString());
            }
            if (filter.getClubPlayingName() != null) {
                sql.append(" AND (c.name LIKE ? OR c2.name LIKE ?)");
                params.add("%" + filter.getClubPlayingName() + "%");
                params.add("%" + filter.getClubPlayingName() + "%");
            }
            if (filter.getMatchAfter() != null) {
                sql.append(" AND m.match_datetime::date > ?");
                params.add(filter.getMatchAfter());
            }
            if (filter.getMatchBeforeOrEquals() != null) {
                sql.append(" AND m.match_datetime::date <= ?");
                params.add(filter.getMatchBeforeOrEquals());
            }
        }

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            stmt.setInt(1, seasonYear);

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 2, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            List<MatchEntity> matches = new ArrayList<>();
            while (rs.next()) {
                matches.add(mapResultsetToMatch(rs));
            }
            return matches;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all matches with filter : " + sql, e);
        }

    }

    public List<MatchEntity> findAll(Integer seasonYear) {
        String sql = "SELECT * FROM \"match\" m JOIN season s ON m.season_id = s.id WHERE s.year = ? ORDER BY m.match_datetime ASC";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, seasonYear);
            ResultSet rs = stmt.executeQuery();
            List<MatchEntity> matches = new ArrayList<>();
            while (rs.next()) {
                matches.add(mapResultsetToMatch(rs));
            }
            return matches;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all matches", e);
        }
    }

    public MatchEntity findById(UUID matchId) {
        String sql = "SELECT * FROM \"match\" WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, matchId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultsetToMatch(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding match by id", e);
        }
    }

    private MatchEntity mapResultsetToMatch(ResultSet rs) throws SQLException {
        MatchEntity match = new MatchEntity();
        match.setId((UUID) rs.getObject("id"));
        match.setStadium(rs.getString("stadium"));
        match.setStatus(PlayingStatus.valueOf(rs.getString("status")));
        match.setMatchDateTime(rs.getTimestamp("match_datetime").toLocalDateTime());
        match.setScoreHome(rs.getLong("score_home"));
        match.setScoreAway(rs.getLong("score_away"));
        match.setHomeClub(clubRepository.findById((UUID) rs.getObject("club_home_id")));
        match.setAwayClub(clubRepository.findById((UUID) rs.getObject("club_away_id")));

        if (match.getHomeClub() == null || match.getAwayClub() == null) {
            throw new RuntimeException("Home or away club not found");
        }

        if (match.getHomeClub().equals(match.getAwayClub())) {
            throw new RuntimeException("Home and away club cannot be the same");
        }

        return match;
    }

    public List<MatchEntity> generateMatches(SeasonEntity season) {
        if (season.getStatus() != PlayingStatus.STARTED) {
            throw new RuntimeException("Season must be started to generate matches");
        }
        List<ClubEntity> clubs = clubRepository.findAll();
        List<MatchEntity> matches = new ArrayList<>();
        for (int i = 0; i < clubs.size(); i++) {
            for (int j = 0; j < clubs.size(); j++) {
                if (i == j) {
                    continue;
                }
                MatchEntity match = new MatchEntity();
                match.setHomeClub(clubs.get(i));
                match.setAwayClub(clubs.get(j));
                match.setStadium(clubs.get(i).getStadium());
                matches.add(save(match));
            }
        }
        return matches;
    }
}
