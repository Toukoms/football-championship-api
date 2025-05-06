package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.ClubStatDTO;
import com.football_championship_api.demo.data.entity.Club;
import com.football_championship_api.demo.data.entity.ClubRanking;

public class ClubStatisticsMapper implements Mapper<ClubRanking, ClubStatDTO> {

    @Override
    public ClubRanking toEntity(ClubStatDTO dto) {
        Club club = new Club();
        club.setId(dto.getId());
        club.setName(dto.getName());
        club.setAcronym(dto.getAcronym());
        club.setYearCreation(dto.getYearCreation());
        club.setStadium(dto.getStadium());
        club.setCoach(dto.getCoach());

        return new ClubRanking(
                club,
                dto.getRankingPoints(),
                dto.getScoredGoals(),
                dto.getConcededGoals(),
                dto.getDifferenceGoals(),
                dto.getCleanSheetNumber()
        );
    }
}
