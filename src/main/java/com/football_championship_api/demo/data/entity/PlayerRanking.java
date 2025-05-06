package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerRanking {
    private int rank;
    private UUID player;
    private Championship championship;
    private int scoredGoals;
    private PlayingTime playingTime;

    public PlayerRanking(UUID player, int scoredGoals, PlayingTime playingTime) {
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playingTime = playingTime;
    }

}
