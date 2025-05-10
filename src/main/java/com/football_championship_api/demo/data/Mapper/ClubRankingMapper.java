package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.ClubRankingDTO;
import com.football_championship_api.demo.data.Entity.ClubRanking;
import org.springframework.stereotype.Component;


@Component
public class ClubRankingMapper {

    public ClubRankingDTO toDTO(ClubRanking entity) {
        ClubRankingDTO dto = new ClubRankingDTO();

        dto.setRank(entity.getRank());
        dto.setClub(entity.getClub());
        dto.setRankingPoints(entity.getRankingPoints());
        dto.setScoredGoals(entity.getScoredGoals());
        dto.setConcededGoals(entity.getConcededGoals());
        dto.setDifferenceGoals(entity.getDifferenceGoals());
        dto.setCleanSheetNumber(entity.getCleanSheetNumber());

        return dto;
    }
}
