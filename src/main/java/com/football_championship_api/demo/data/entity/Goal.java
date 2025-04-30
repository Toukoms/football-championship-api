package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    private UUID id;
    private int minute;
    private Boolean ownGoal;
    private Match match;
    private Club club;
    private Player player;
}
