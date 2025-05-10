package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.MatchEntity;
import com.football_championship_api.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchMakerController {
    private final MatchService matchService;
    @PostMapping("/match-maker/{seasonYear}")
    public ResponseEntity<List<MatchEntity>> createMatches(
            @PathVariable Integer seasonYear
    ) {
        throw new UnsupportedOperationException("Not yet implemented");
        // return ResponseEntity.ok(matchService.createMatches(seasonYear));
    }
}
