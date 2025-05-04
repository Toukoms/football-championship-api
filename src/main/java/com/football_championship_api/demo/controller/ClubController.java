package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.ClubEntity;
import com.football_championship_api.demo.data.entity.ClubStatistics;
import com.football_championship_api.demo.data.entity.PlayerEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {
    @GetMapping
    public ResponseEntity<List<ClubEntity>> getClubs() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PutMapping
    public ResponseEntity<List<ClubEntity>> createOrUpdateClubs() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerEntity>> getPlayersOfClubById() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PutMapping("/{id}/players")
    public ResponseEntity<List<PlayerEntity>> changePlayersToClubById(
            @RequestBody List<PlayerEntity> players
    ) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PostMapping("/{id}/players")
    public ResponseEntity<List<PlayerEntity>> addPlayersToClubById(
            @RequestBody List<PlayerEntity> players
    ) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @GetMapping("/{id}/statistics/{seasonYear}")
    public ResponseEntity<List<ClubStatistics>> getStatisticsOfClubById() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
