package com.football_championship_api.demo.data.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChampionshipRanking {
    private int rank;
    private Championship championship;
    private int differenceGoalsMedian;
}
