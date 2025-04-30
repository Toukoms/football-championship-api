package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Match {
    private UUID id;
    private String stadium;
    private LocalDateTime matchDateTime;
    private MatchStatus status;
    private int score_home;
    private int score_away;

    private Season season;
    private Club homeClub;
    private Club awayClub;

    private List<Player> players;
    private List<Club> clubs;

    public Match() {
        if (homeClub.equals(awayClub)) {
            throw new IllegalArgumentException("Home Club and Away Club cannot be equal");
        }
    }
}
