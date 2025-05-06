package com.football_championship_api.demo.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubRanking {
    private int rank;
    private Club club;
    private int rankingPoints;
    private int scoredGoals;
    private int concededGoals;
    private int differenceGoals;
    private int cleanSheetNumber;

    public ClubRanking(Club club, int rankingPoints, int scoredGoals, int concededGoals, int differenceGoals, int cleanSheetNumber) {
        this.club = club;
        this.rankingPoints = rankingPoints;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.differenceGoals = differenceGoals;
        this.cleanSheetNumber = cleanSheetNumber;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
        updateDifferenceGoals();
    }

    public void setConcededGoals(int concededGoals) {
        this.concededGoals = concededGoals;
        updateDifferenceGoals();
    }

    // Car DifferenceGoals est décrit comme la difference entre les ScoredGoals et les ConcededGoals
    private void updateDifferenceGoals() {
        this.differenceGoals = this.scoredGoals - this.concededGoals;
    }


}
