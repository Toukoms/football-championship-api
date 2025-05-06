package com.football_championship_api.demo.data.Repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDAO implements GeneralDAO<Player>{

    private final CustomDataSource dataSource;

    @Autowired
    public PlayerDAO(CustomDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void insert(Player player) {
        String sql = "INSERT INTO player (id, name, number, position, nationality, age, current_club_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, player.getId());
            stmt.setString(2, player.getName());
            stmt.setInt(3, player.getNumber());
            stmt.setString(4, player.getPosition().name());
            stmt.setString(5, player.getNationality());
            stmt.setInt(6, player.getAge());
            stmt.setObject(7, player.getCurrentClub().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting player", e);
        }
    }
}
