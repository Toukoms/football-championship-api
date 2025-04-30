package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalEntity {
    private UUID id;
    private Long minute;
    private Boolean ownGoal;
    private MatchEntity match;
    private ClubEntity club;
    private PlayerEntity player;
}
