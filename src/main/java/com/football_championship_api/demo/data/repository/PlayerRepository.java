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
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public abstract class PlayerRepository implements BaseRepository<PlayerEntity, UUID> {
    private final CustomDataSource dataSource;

    @Override
    public Optional<PlayerEntity> findById(UUID id) {
        String sql = "SELECT p.*, c.id as club_id, c.name as club_name FROM players p " +
                    "LEFT JOIN clubs c ON p.current_club_id = c.id " +
                    "WHERE p.id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToPlayer(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding player by id", e);
        }
    }

    @Override
    public List<PlayerEntity> findAll() {
        String sql = "SELECT p.*, c.id as club_id, c.name as club_name FROM players p " +
                    "LEFT JOIN clubs c ON p.current_club_id = c.id";
        List<PlayerEntity> players = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all players", e);
        }
    }

    @Override
    public List<PlayerEntity> findAll(Filter filter) {
        StringBuilder sql = new StringBuilder(
                "SELECT p.*, c.id as club_id, c.name as club_name FROM players p " +
                        "LEFT JOIN clubs c ON p.current_club_id = c.id"
        );

        List<Object> params = new ArrayList<>();
        if (filter != null && !filter.getCriteria().isEmpty()) {
            sql.append(" WHERE ");
            for (int i = 0; i < filter.getCriteria().size(); i++) {
                FilterCriteria criteria = filter.getCriteria().get(i);
                if (i > 0) {
                    sql.append(" AND ");
                }

                switch (criteria.getOperation()) {
                    case EQUALS:
                        sql.append("p.").append(criteria.getField()).append(" = ?");
                        params.add(criteria.getValue());
                        break;
                    case NOT_EQUALS:
                        sql.append("p.").append(criteria.getField()).append(" != ?");
                        params.add(criteria.getValue());
                        break;
                    case GREATER_THAN:
                        sql.append("p.").append(criteria.getField()).append(" > ?");
                        params.add(criteria.getValue());
                        break;
                    case LESS_THAN:
                        sql.append("p.").append(criteria.getField()).append(" < ?");
                        params.add(criteria.getValue());
                        break;
                    case GREATER_THAN_OR_EQUALS:
                        sql.append("p.").append(criteria.getField()).append(" >= ?");
                        params.add(criteria.getValue());
                        break;
                    case LESS_THAN_OR_EQUALS:
                        sql.append("p.").append(criteria.getField()).append(" <= ?");
                        params.add(criteria.getValue());
                        break;
                    case LIKE:
                        sql.append("p.").append(criteria.getField()).append(" LIKE ?");
                        params.add("%" + criteria.getValue() + "%");
                        break;
                    case IN:
                        sql.append("p.").append(criteria.getField()).append(" IN (?)");
                        params.add(criteria.getValue());
                        break;
                    case IS_NULL:
                        sql.append("p.").append(criteria.getField()).append(" IS NULL");
                        break;
                    case IS_NOT_NULL:
                        sql.append("p.").append(criteria.getField()).append(" IS NOT NULL");
                        break;
                }
            }
        }

        if (filter != null && !filter.getOrderBy().isEmpty()) {
            sql.append(" ORDER BY ");
            sql.append(String.join(", ", filter.getOrderBy().stream()
                    .map(field -> "p." + field)
                    .toList()));
        }

        List<PlayerEntity> players = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding players with filter", e);
        }
    }

    @Override
    public PlayerEntity save(PlayerEntity player) {
        String sql = "INSERT INTO players (id, name, number, position, nationality, age, created_at, updated_at, current_club_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                    "ON CONFLICT (id) DO UPDATE SET " +
                    "name = EXCLUDED.name, " +
                    "number = EXCLUDED.number, " +
                    "position = EXCLUDED.position, " +
                    "nationality = EXCLUDED.nationality, " +
                    "age = EXCLUDED.age, " +
                    "updated_at = ?, " +
                    "current_club_id = EXCLUDED.current_club_id " +
                    "RETURNING *";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            LocalDateTime now = LocalDateTime.now();
            UUID playerId = player.getId() != null ? player.getId() : UUID.randomUUID();
            
            stmt.setObject(1, playerId);
            stmt.setString(2, player.getName());
            stmt.setLong(3, player.getNumber());
            stmt.setString(4, player.getPosition().name());
            stmt.setString(5, player.getNationality());
            stmt.setLong(6, player.getAge());
            stmt.setObject(7, player.getCreatedAt() != null ? player.getCreatedAt() : now);
            stmt.setObject(8, now);
            stmt.setObject(9, player.getCurrentClub() != null ? player.getCurrentClub().getId() : null);
            stmt.setObject(10, now);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPlayer(rs);
            }
            throw new RuntimeException("Failed to save player");
        } catch (SQLException e) {
            throw new RuntimeException("Error saving player", e);
        }
    }

    @Override
    public List<PlayerEntity> saveAll(List<PlayerEntity> players) {
        List<PlayerEntity> savedPlayers = new ArrayList<>();
        for (PlayerEntity player : players) {
            savedPlayers.add(save(player));
        }
        return savedPlayers;
    }

    @Override
    public void deleteById(UUID id) {
        String sql = "DELETE FROM players WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting player", e);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return findById(id).isPresent();
    }

    private PlayerEntity mapResultSetToPlayer(ResultSet rs) throws SQLException {
        PlayerEntity player = new PlayerEntity();
        player.setId((UUID) rs.getObject("id"));
        player.setName(rs.getString("name"));
        player.setNumber(rs.getLong("number"));
        player.setPosition(PlayerPosition.valueOf(rs.getString("position")));
        player.setNationality(rs.getString("nationality"));
        player.setAge(rs.getLong("age"));
        player.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        player.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));

        // Map club if exists
        UUID clubId = (UUID) rs.getObject("club_id");
        if (clubId != null) {
            ClubEntity club = new ClubEntity();
            club.setId(clubId);
            club.setName(rs.getString("club_name"));
            player.setCurrentClub(club);
        }

        return player;
    }
}