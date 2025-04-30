package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.SeasonEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    @GetMapping
    public ResponseEntity<List<SeasonEntity>> getSeasons() {
        throw new UnsupportedOperationException("Not yet implemented");
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
