package com.football_championship_api.demo.controller;

import com.football_championship_api.demo.data.entity.MatchEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchMakerController {
    @PostMapping("/match-maker/{seasonYear}")
    public ResponseEntity<List<MatchEntity>> createMatches() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
