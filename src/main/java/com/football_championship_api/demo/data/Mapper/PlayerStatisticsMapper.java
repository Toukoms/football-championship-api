package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.PlayerStatDTO;
import com.football_championship_api.demo.data.entity.PlayerRanking;
import com.football_championship_api.demo.data.entity.PlayingTime;

import java.util.UUID;

public class PlayerStatisticsMapper implements Mapper<PlayerRanking, PlayerStatDTO> {

    @Override
    public PlayerRanking toEntity(PlayerStatDTO dto) {
        PlayerRanking playerRanking = new PlayerRanking();

        playerRanking.setPlayer(dto.getId());
        playerRanking.setScoredGoals(dto.getScoredGoal());
        playerRanking.setPlayingTime(dto.getPlayingTime());

        return playerRanking;
    }
}
