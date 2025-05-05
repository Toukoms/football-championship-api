package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.config.CustomDataSource;
import com.football_championship_api.demo.data.entity.PlayerStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlayerStatisticsRepository {
    private final CustomDataSource dataSource;

    public PlayerStatistics getStatisticsOfPlayerById(UUID playerId, Integer seasonYear) {
        return null;
    }
}
