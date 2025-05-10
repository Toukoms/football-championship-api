package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchEntity {
    private UUID id;
    private String stadium;
    private LocalDateTime matchDateTime;
    private PlayingStatus status;
    private Long scoreHome;
    private Long scoreAway;

    private SeasonEntity season;
    private ClubEntity homeClub;
    private ClubEntity awayClub;
}
