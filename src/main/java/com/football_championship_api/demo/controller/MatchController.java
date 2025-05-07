package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.MatchEntity;
import com.football_championship_api.demo.data.entity.PlayingStatus;
import com.football_championship_api.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/{seasonYear}")
    public ResponseEntity<List<MatchEntity>> getMatchesBySeasonYear(
            @PathVariable Integer seasonYear,
            @RequestParam(required = false) PlayingStatus matchStatus,
            @RequestParam(required = false) String clubPlayingName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate matchAfter,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate matchBeforeOrEquals
    ) {
        return ResponseEntity.ok(matchService.getMatchesBySeasonYear(seasonYear, matchStatus, clubPlayingName, matchAfter, matchBeforeOrEquals));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MatchEntity> updateMatchStatus() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @PostMapping("/{id}/goals")
    public ResponseEntity<MatchEntity> addGoalsToMatch() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
