package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MatchEntity {
    private UUID id;
    private String stadium;
    private LocalDateTime matchDateTime;
    private MatchStatus status;
    private Long score_home;
    private Long score_away;

    private SeasonEntity season;
    private ClubEntity homeClub;
    private ClubEntity awayClub;

    private List<PlayerEntity> players;
    private List<ClubEntity> clubs;

    public MatchEntity() {
        if (homeClub.equals(awayClub)) {
            throw new IllegalArgumentException("Home Club and Away Club cannot be equal");
        }
    }
}
