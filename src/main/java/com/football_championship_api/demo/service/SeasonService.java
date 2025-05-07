package com.football_championship_api.demo.service;

import com.football_championship_api.demo.data.entity.SeasonEntity;
import com.football_championship_api.demo.data.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public List<SeasonEntity> getSeasons() {
        return seasonRepository.findAll();
    }

    public List<SeasonEntity> createSeasons(List<SeasonEntity> seasons) {
        return seasonRepository.saveAll(seasons);
    }

    public SeasonEntity updateStatus(Integer seasonYear) {
        return seasonRepository.updateStatus(seasonYear);
    }
}
