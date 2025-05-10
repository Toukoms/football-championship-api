package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.SeasonDTO;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public int getSeasonYear(SeasonDTO seasonDTO){

        return seasonDTO.getYear();
    }
}
