package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.Entity.Club;
import com.football_championship_api.demo.data.Entity.ClubRanking;
import com.football_championship_api.demo.data.Entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Repository
public class ClubDAO  implements GeneralDAO<Club>{
    private final CustomDataSource dataSource;

    @Autowired
    public ClubDAO(CustomDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    @Override
    public void insert(Club club) {
        String insertCoachSql = "INSERT INTO coach (id, name, nationality, created_at) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, nationality = EXCLUDED.nationality, created_at = EXCLUDED.created_at";
        String insertClubSql = "INSERT INTO club (id, name, acronym, stadium, coach_id, created_at) VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, acronym = EXCLUDED.acronym, stadium = EXCLUDED.stadium, coach_id = EXCLUDED.coach_id, created_at = EXCLUDED.created_at";

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);


            try (PreparedStatement coachStmt = connection.prepareStatement(insertCoachSql)) {
                Coach coach = club.getCoach();
                coachStmt.setObject(1, coach.getId());
                coachStmt.setString(2, coach.getName());
                coachStmt.setString(3, coach.getNationality());
                coachStmt.setInt(4, coach.getCreatedAt());
                coachStmt.executeUpdate();
            }


            try (PreparedStatement clubStmt = connection.prepareStatement(insertClubSql)) {
                clubStmt.setObject(1, club.getId());
                clubStmt.setString(2, club.getName());
                clubStmt.setString(3, club.getAcronym());
                clubStmt.setString(4, club.getStadium());
                clubStmt.setObject(5, club.getCoach().getId());
                clubStmt.setInt(6, club.getYearCreation());
                clubStmt.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting club and coach", e);
        }
    }




}
