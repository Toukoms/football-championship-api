package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.ClubStatisticsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClubStatisticsRepository {
    private final CustomDataSource dataSource;
    private final ClubRepository clubRepository;
    private final SeasonRepository seasonRepository;

    public List<ClubStatisticsEntity> getStatisticsOfClubs(Integer seasonYear) {
        String sql = "SELECT * FROM club_statistics cs JOIN season s ON cs.season_id = s.id WHERE s.year = ? ORDER By ranking_points DESC, difference_goals DESC, clean_sheet_number DESC";
        try (Connection conn = dataSource.getConnection(); java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, seasonYear);
            ResultSet rs = stmt.executeQuery();
            List<ClubStatisticsEntity> savedClubStatistics = new ArrayList<>();
            while (rs.next()) {
                savedClubStatistics.add(mapResultSetToClubStatistics(rs));
            }
            return savedClubStatistics;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding statistics of club by id", e);
        }
    }

    private ClubStatisticsEntity mapResultSetToClubStatistics(java.sql.ResultSet rs) throws java.sql.SQLException {
        ClubStatisticsEntity clubStatisticsEntity = new ClubStatisticsEntity();
        clubStatisticsEntity.setScoredGoals(rs.getLong("scored_goals"));
        clubStatisticsEntity.setConcededGoals(rs.getLong("conceded_goals"));
        clubStatisticsEntity.setRankingPoints(rs.getLong("ranking_points"));
        clubStatisticsEntity.setDifferenceGoals(rs.getLong("difference_goals"));
        clubStatisticsEntity.setCleanSheetNumber(rs.getLong("clean_sheet_number"));
        clubStatisticsEntity.setClub(clubRepository.findById((UUID) rs.getObject("club_id")));
        clubStatisticsEntity.setSeason(seasonRepository.findById((UUID) rs.getObject("season_id")));

        return clubStatisticsEntity;
    }
}
