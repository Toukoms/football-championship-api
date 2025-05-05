package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.ClubEntity;
import com.football_championship_api.demo.data.entity.PlayerEntity;
import com.football_championship_api.demo.data.entity.PlayerPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlayerRepository {
    
    private final CustomDataSource dataSource;

    public List<PlayerEntity> findAll() {
        return findAll(null);
    }

    public List<PlayerEntity> findAll(FilterPlayer filter) {
        StringBuilder sql = new StringBuilder("SELECT p.*, c.* FROM player p JOIN club c ON p.current_club_id = c.id");
        List<Object> params = new ArrayList<>();
        
        if (filter != null) {
            sql.append(" WHERE 1=1");
            if (filter.getName() != null) {
                sql.append(" AND LOWER(p.name) LIKE LOWER(?)");
                params.add("%" + filter.getName() + "%");
            }
            if (filter.getAgeMinimum() != null) {
                sql.append(" AND p.age >= ?");
                params.add(filter.getAgeMinimum());
            }
            if (filter.getAgeMaximum() != null) {
                sql.append(" AND p.age <= ?");
                params.add(filter.getAgeMaximum());
            }
            if (filter.getClubName() != null) {
                sql.append(" AND LOWER(c.name) LIKE LOWER(?)");
                params.add("%" + filter.getClubName() + "%");
            }
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            
            ResultSet rs = stmt.executeQuery();
            List<PlayerEntity> player = new ArrayList<>();
            
            while (rs.next()) {
                player.add(mapResultSetToPlayer(rs));
            }
            
            return player;
            
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all player", e);
        }
    }

    public PlayerEntity save(PlayerEntity entity) {
        if (entity.getId() == null) {
            return insert(entity);
        }
        return update(entity);
    }

    public List<PlayerEntity> saveAll(List<PlayerEntity> entities) {
        List<PlayerEntity> savedEntities = new ArrayList<>();
        for (PlayerEntity entity : entities) {
            savedEntities.add(save(entity));
        }
        return savedEntities;
    }

    private PlayerEntity insert(PlayerEntity entity) {
        String sql = "INSERT INTO player (id, name, number, position, nationality, age, created_at, updated_at, current_club_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            UUID id = UUID.randomUUID();
            LocalDateTime now = LocalDateTime.now();
            
            stmt.setObject(1, id);
            stmt.setString(2, entity.getName());
            stmt.setLong(3, entity.getNumber());
            stmt.setString(4, entity.getPosition().name());
            stmt.setString(5, entity.getNationality());
            stmt.setLong(6, entity.getAge());
            stmt.setTimestamp(7, Timestamp.valueOf(now));
            stmt.setTimestamp(8, Timestamp.valueOf(now));
            stmt.setObject(9, entity.getCurrentClub() != null ? entity.getCurrentClub().getId() : null);
            
            stmt.executeUpdate();
            
            entity.setId(id);
            entity.setCreatedAt(now);
            entity.setUpdatedAt(now);
            return entity;
            
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting player", e);
        }
    }

    private PlayerEntity update(PlayerEntity entity) {
        String sql = "UPDATE player SET name = ?, number = ?, position = ?, nationality = ?, age = ?, " +
                    "updated_at = ?, current_club_id = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            LocalDateTime now = LocalDateTime.now();
            
            stmt.setString(1, entity.getName());
            stmt.setLong(2, entity.getNumber());
            stmt.setString(3, entity.getPosition().name());
            stmt.setString(4, entity.getNationality());
            stmt.setLong(5, entity.getAge());
            stmt.setTimestamp(6, Timestamp.valueOf(now));
            stmt.setObject(7, entity.getCurrentClub() != null ? entity.getCurrentClub().getId() : null);
            stmt.setObject(8, entity.getId());
            
            stmt.executeUpdate();
            
            entity.setUpdatedAt(now);
            return entity;
            
        } catch (SQLException e) {
            throw new RuntimeException("Error updating player", e);
        }
    }

    private PlayerEntity mapResultSetToPlayer(ResultSet rs) throws SQLException {
        PlayerEntity player = new PlayerEntity();
        player.setId((UUID) rs.getObject("id"));
        player.setName(rs.getString("name"));
        player.setNumber(rs.getLong("number"));
        player.setPosition(PlayerPosition.valueOf(rs.getString("position")));
        player.setNationality(rs.getString("nationality"));
        player.setAge(rs.getInt("age"));
        player.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        player.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        
        return player;
    }
}