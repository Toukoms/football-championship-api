package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.ClubEntity;
import com.football_championship_api.demo.data.entity.ClubStatisticsEntity;
import com.football_championship_api.demo.data.entity.PlayerEntity;
import com.football_championship_api.demo.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ClubEntity>> getClubs() {
        return ResponseEntity.ok(clubService.getClubs());
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
    public ResponseEntity<List<PlayerEntity>> changePlayersToClubById(@RequestBody List<PlayerEntity> players) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PostMapping("/{id}/players")
    public ResponseEntity<List<PlayerEntity>> addPlayersToClubById(@RequestBody List<PlayerEntity> players) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @GetMapping("/{id}/statistics/{seasonYear}")
    public ResponseEntity<ClubStatisticsEntity> getStatisticsOfClubById(@PathVariable UUID id, @PathVariable Integer seasonYear) {
        return ResponseEntity.ok(clubService.getStatisticsOfClubById(id, seasonYear));
    }
}
