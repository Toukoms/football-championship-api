package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.SeasonEntity;
import com.football_championship_api.demo.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<SeasonEntity>> createSeasons() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PutMapping("/{seasonYear}/status")
    public ResponseEntity<SeasonEntity> updateSeasonStatus() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
