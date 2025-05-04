package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.MatchEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    @GetMapping("/{seasonYear}")
    public ResponseEntity<List<MatchEntity>> getMatchesBySeasonYear(
            @PathVariable Integer seasonYear,
            @RequestParam(required = false) String matchStatus,
            @RequestParam(required = false) String clubPlayingName,
            @RequestParam(required = false) String matchAfter,
            @RequestParam(required = false) String matchBeforeOrEquals
    ) {
        throw new UnsupportedOperationException("Not yet implemented");
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
