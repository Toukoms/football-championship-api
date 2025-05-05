package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatisticsEntity {
    private Long scoredGoals = 0L;
    private Long playingTimeValue;
    private PlayingTimeUnit playingTimeUnit;
    private PlayerEntity player;
    private SeasonEntity season;
}
