package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.ClubRanking;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "INSERT INTO club_ranking (club_id, ranking_points, scored_goals, conceded_goals, difference_goals, clean_sheet_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

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
}
