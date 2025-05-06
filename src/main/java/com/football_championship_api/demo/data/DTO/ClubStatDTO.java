package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.entity.Club;
import com.football_championship_api.demo.data.entity.Coach;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubStatDTO {
    private int rank;

    private UUID id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private Coach coach;

    private int rankingPoints;
    private int scoredGoals;
    private int concededGoals;
    private int differenceGoals;
    private int cleanSheetNumber;


    public ClubStatDTO(UUID id, String name, String acronym, int yearCreation, String stadium, Coach coach, int rankingPoints, int scoredGoals, int concededGoals, int differenceGoals, int cleanSheetNumber) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.yearCreation = yearCreation;
        this.stadium = stadium;
        this.coach = coach;
        this.rankingPoints = rankingPoints;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.differenceGoals = differenceGoals;
        this.cleanSheetNumber = cleanSheetNumber;
    }
}
