package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.ClubStatDTO;
import com.football_championship_api.demo.data.Entity.Club;
import com.football_championship_api.demo.data.Entity.ClubRanking;
import org.springframework.stereotype.Component;

@Component
public class ClubStatisticsMapper implements Mapper<ClubRanking, ClubStatDTO> {

    @Override
    public ClubRanking toEntity(ClubStatDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ClubStatDTO cannot be null");
        }

        Club club = dto.getClub();
        if (club == null) {
            throw new IllegalArgumentException("Club in ClubStatDTO cannot be null");
        }
        if (club.getId() == null || club.getName() == null || club.getAcronym() == null) {
            throw new IllegalArgumentException("Club id, name, and acronym cannot be null");
        }

        // Créer le ClubRanking à partir du club et des statistiques
        ClubRanking clubRanking = new ClubRanking(
                club,
                dto.getRankingPoints(),
                dto.getScoredGoals(),
                dto.getConcededGoals(),
                dto.getDifferenceGoals(),
                dto.getCleanSheetNumber()
        );

        return clubRanking;
    }
}
