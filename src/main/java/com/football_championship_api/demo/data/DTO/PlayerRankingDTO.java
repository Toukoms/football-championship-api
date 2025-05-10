package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.Entity.Championship;
import com.football_championship_api.demo.data.Entity.PlayingTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerRankingDTO {
    private int rank;
    private UUID player;
    private Championship championship;
    private int scoredGoals;
    private PlayingTime playingTime;
}
