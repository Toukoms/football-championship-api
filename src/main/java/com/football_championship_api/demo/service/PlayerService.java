package com.football_championship_api.demo.service;

import com.football_championship_api.demo.model.ClubPlayer;
import com.football_championship_api.demo.model.Player;
import com.football_championship_api.demo.model.PlayerStatistics;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerService {
    
    /**
     * Get list of players with optional filters
     *
     * @param name Filter by name (case-insensitive contains)
     * @param ageMinimum Filter by minimum age
     * @param ageMaximum Filter by maximum age
     * @param clubName Filter by club name (case-insensitive contains)
     * @return List of players with their club information
     */
    public List<ClubPlayer> getPlayers(String name, Integer ageMinimum, Integer ageMaximum, String clubName) {
        // TODO: Implement filtering logic
        return List.of();
    }

    /**
     * Create or update players without attaching them to a club
     *
     * @param players List of players to create or update
     * @return List of created/updated players
     */
    public List<Player> createOrUpdatePlayers(List<Player> players) {
        // TODO: Implement create/update logic
        return List.of();
    }

    /**
     * Get statistics for a specific player in a given season
     * Note: Own goals are not considered as goals scored
     *
     * @param playerId Player identifier
     * @param seasonYear Season year
     * @return Player statistics
     * @throws ResourceNotFoundException if player or season not found
     */
    public PlayerStatistics getStatisticsOfPlayerById(String playerId, LocalDate seasonYear) {
        // TODO: Implement statistics retrieval logic
        return null;
    }
}