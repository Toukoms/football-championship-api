package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.Club;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "INSERT INTO club (id, name, acronym, stadium, coach_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, club.getId());
            stmt.setString(2, club.getName());
            stmt.setString(3, club.getAcronym());
            stmt.setString(4, club.getStadium());
            stmt.setObject(5, club.getCoach().getId()); // UUID ici aussi

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting club", e);
        }
    }
}
