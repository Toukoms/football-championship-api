package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.PlayerEntity;
import com.football_championship_api.demo.data.entity.PlayerStatisticsEntity;
import com.football_championship_api.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerEntity>> getPlayers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "ageMinimum", required = false) int ageMinimum,
            @RequestParam(name = "ageMaximum", required = false) int ageMaximum,
            @RequestParam(name = "clubName", required = false) String clubName
    ) {
        return ResponseEntity.ok(playerService.getPlayers(name, ageMinimum, ageMaximum, clubName));
    }

    @PutMapping
    public ResponseEntity<Object> createOrUpdatePlayers(
            @RequestBody List<PlayerEntity> players
    ) {
        try {
            return ResponseEntity.ok(playerService.createOrUpdatePlayers(players));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Player with the same name and number already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/statistics/{seasonYear}")
    public ResponseEntity<PlayerStatisticsEntity> getStatisticsOfPlayerById(
            @PathVariable UUID id,
            @PathVariable Integer seasonYear
    ) {
        return ResponseEntity.ok(playerService.getStatisticsOfPlayerById(id, seasonYear));
    }
}
