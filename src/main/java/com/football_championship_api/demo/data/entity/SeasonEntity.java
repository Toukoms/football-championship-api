package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonEntity {
    private UUID id;
    private Integer year;
    private String alias;
    private PlayingStatus status;
    private int createdAt;

    private List<MatchEntity> matches;
}
