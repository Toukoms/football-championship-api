package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.Entity.Club;
import com.football_championship_api.demo.data.Entity.ClubRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class ClubRankingDAO implements GeneralDAO<ClubRanking>{
    private final CustomDataSource dataSource;

    @Autowired
    public ClubRankingDAO(CustomDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public void insert(ClubRanking ranking) {
        String sql = "INSERT INTO club_ranking (club_id, ranking_points, scored_goals, conceded_goals, difference_goals, clean_sheet_number) VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (club_id) DO UPDATE SET ranking_points = EXCLUDED.ranking_points, scored_goals = EXCLUDED.scored_goals, conceded_goals = EXCLUDED.conceded_goals, difference_goals = EXCLUDED.difference_goals, clean_sheet_number = EXCLUDED.clean_sheet_number;";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, ranking.getClub().getId());
            stmt.setInt(2, ranking.getRankingPoints());
            stmt.setInt(3, ranking.getScoredGoals());
            stmt.setInt(4, ranking.getConcededGoals());
            stmt.setInt(5, ranking.getDifferenceGoals());
            stmt.setInt(6, ranking.getCleanSheetNumber());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting club ranking", e);
        }
    }

    public List<ClubRanking> findBestClubs(int top){
        List<ClubRanking> ranking = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.acronym, c.year_creation, c.stadium, cr.ranking_points, cr.scored_goals, cr.conceded_goals, cr.difference_goals, cr.clean_sheet_number " +
                "FROM club_ranking cr " +
                "JOIN club c ON cr.club_id = c.id " +
                "ORDER BY cr.ranking_points DESC LIMIT ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, top);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Club club = new Club();
                    club.setId(UUID.fromString(rs.getString("id")));
                    club.setName(rs.getString("name"));
                    club.setAcronym(rs.getString("acronym"));
                    club.setYearCreation(rs.getInt("year_creation"));
                    club.setStadium(rs.getString("stadium"));

                    ClubRanking clubRanking = new ClubRanking();
                    clubRanking.setClub(club);
                    clubRanking.setRankingPoints(rs.getInt("ranking_points"));
                    clubRanking.setScoredGoals(rs.getInt("scored_goals"));
                    clubRanking.setConcededGoals(rs.getInt("conceded_goals"));
                    clubRanking.setDifferenceGoals(rs.getInt("difference_goals"));
                    clubRanking.setCleanSheetNumber(rs.getInt("clean_sheet_number"));

                    ranking.add(clubRanking);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving club rankings", e);
        }

        return ranking;
    }


}
