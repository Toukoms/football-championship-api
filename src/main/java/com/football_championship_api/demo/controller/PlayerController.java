package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.PlayerEntity;
import com.football_championship_api.demo.data.entity.PlayerStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @GetMapping
    public ResponseEntity<List<PlayerEntity>> getPlayers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer ageMinimum,
            @RequestParam(required = false) Integer ageMaximum,
            @RequestParam(required = false) String clubName
    ) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PutMapping
    public ResponseEntity<PlayerEntity> createOrUpdatePlayers() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @GetMapping("/{id}/statistics/{seasonYear}")
    public ResponseEntity<List<PlayerStatistics>> getStatisticsOfPlayerById() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
