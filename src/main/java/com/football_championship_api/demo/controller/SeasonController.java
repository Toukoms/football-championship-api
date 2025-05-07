package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.controller.restMapper.SeasonRest;
import com.football_championship_api.demo.data.entity.SeasonEntity;
import com.football_championship_api.demo.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping
    public ResponseEntity<List<SeasonEntity>> getSeasons() {
        return ResponseEntity.ok(seasonService.getSeasons());
    }

    @PostMapping
    public ResponseEntity<List<SeasonEntity>> createSeasons(
            List<SeasonRest> seasons
    ) {
        List<SeasonEntity> seasonEntities = new ArrayList<>();
        seasons.forEach(s -> {
            SeasonEntity season = new SeasonEntity();
            season.setYear(s.getYear());
            season.setAlias(s.getAlias());
            seasonEntities.add(season);
        });
        return ResponseEntity.ok(seasonService.createSeasons(seasonEntities));
    }

    @PutMapping("/{seasonYear}/status")
    public ResponseEntity<SeasonEntity> updateSeasonStatus() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
