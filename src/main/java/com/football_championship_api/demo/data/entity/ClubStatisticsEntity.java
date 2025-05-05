package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubStatisticsEntity {
    private Long rankingPoints = 0L;
    private Long scoredGoals = 0L;
    private Long concededGoals = 0L;
    private Long differenceGoals = 0L;
    private Long cleanSheetNumber = 0L;
    private ClubEntity club;
    private SeasonEntity season;
}
