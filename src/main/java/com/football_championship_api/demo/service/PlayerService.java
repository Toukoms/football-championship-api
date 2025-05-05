package com.football_championship_api.demo.service;

import com.football_championship_api.demo.data.entity.PlayerEntity;
import com.football_championship_api.demo.data.entity.PlayerStatistics;
import com.football_championship_api.demo.data.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final CoachRepository coachRepository;
    private final PlayerStatisticsRepository playerStatisticsRepository;

    /**
     * Get a list of players with optional filters
     *
     * @param name       Filter by name (case-insensitive contains)
     * @param ageMinimum Filter by minimum age
     * @param ageMaximum Filter by maximum age
     * @param clubName   Filter by club name (case-insensitive contains)
     * @return List of players with their club information
     */
    public List<PlayerEntity> getPlayers(String name, Integer ageMinimum, Integer ageMaximum, String clubName) {
        if (name == null && ageMinimum == null && ageMaximum == null && clubName == null) {
            return playerRepository.findAll();
        }

        FilterPlayer filter = new FilterPlayer();
        filter.setName(name);
        filter.setAgeMaximum(ageMaximum);
        filter.setAgeMinimum(ageMinimum);
        filter.setClubName(clubName);

        return playerRepository.findAll(filter);
    }

    /**
     * Create or update players without attaching them to a club
     *
     * @param players List of players to create or update
     * @return List of created/updated players
     */
    public List<PlayerEntity> createOrUpdatePlayers(List<PlayerEntity> players) {
        // TODO: Implement create/update logic
        return List.of();
    }

    /**
     * Get statistics for a specific player in a given season
     * Note: Own goals are not considered as goals scored
     *
     * @param playerId   Player identifier
     * @param seasonYear Season year
     * @return Player statistics
     * @throws IllegalArgumentException if player or season not found
     */
    public PlayerStatistics getStatisticsOfPlayerById(UUID playerId, Integer seasonYear) {
        if (playerId == null || seasonYear == null) {
            throw new IllegalArgumentException("Player ID and season year must be provided");
        }
        PlayerEntity player = playerRepository.findById(playerId);
        PlayerStatistics playerStatistics = playerStatisticsRepository.getStatisticsOfPlayerById(playerId, seasonYear);
        playerStatistics.setPlayer(player);
        return playerStatistics;
    }
}