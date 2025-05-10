package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.ClubDTO;
import com.football_championship_api.demo.data.Entity.Club;
import org.springframework.stereotype.Component;


@Component
public class ClubMapper implements Mapper<Club, ClubDTO> {

    @Override
    public Club toEntity(ClubDTO dto) {
        Club club = new Club();

        club.setId(dto.getId());
        club.setName(dto.getName());
        club.setAcronym(dto.getAcronym());
        club.setYearCreation(dto.getYearCreation());
        club.setStadium(dto.getStadium());
        club.setCoach(dto.getCoach());

        return club;
    }
}
