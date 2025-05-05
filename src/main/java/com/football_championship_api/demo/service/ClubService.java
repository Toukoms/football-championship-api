package com.football_championship_api.demo.service;

import com.football_championship_api.demo.data.entity.ClubEntity;
import com.football_championship_api.demo.data.entity.ClubStatisticsEntity;
import com.football_championship_api.demo.data.repository.ClubRepository;
import com.football_championship_api.demo.data.repository.ClubStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubStatisticsRepository clubStatisticsRepository;

    public List<ClubEntity> getClubs() {
        return clubRepository.findAll();
    }

    public ClubStatisticsEntity getStatisticsOfClubById(UUID clubId, Integer seasonYear) {
        if (clubId == null || seasonYear == null) {
            throw new IllegalArgumentException("Club ID and season year must be provided");
        }
        return clubStatisticsRepository.getStatisticsOfClubById(clubId, seasonYear);
    }
}
