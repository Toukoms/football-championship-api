package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.PlayerRankingDTO;
import com.football_championship_api.demo.data.Entity.PlayerRanking;
import org.springframework.stereotype.Component;


@Component
public class PlayerRankingMapper {

    public PlayerRankingDTO toDTO(PlayerRanking entity) {
        PlayerRankingDTO dto = new PlayerRankingDTO();

        dto.setRank(entity.getRank());
        dto.setPlayer(entity.getPlayer());
        dto.setChampionship(entity.getChampionship());
        dto.setScoredGoals(entity.getScoredGoals());
        dto.setPlayingTime(entity.getPlayingTime());

        return dto;
    }
}
