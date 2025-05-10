package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.PlayerStatDTO;
import com.football_championship_api.demo.data.Entity.PlayerRanking;
import com.football_championship_api.demo.data.Entity.PlayingTime;
import com.football_championship_api.demo.data.Entity.DurationUnit;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlayerStatisticsMapper implements Mapper<PlayerRanking, PlayerStatDTO> {

    @Override
    public PlayerRanking toEntity(PlayerStatDTO dto) {

        PlayerRanking playerRanking = new PlayerRanking();

        playerRanking.setPlayer(dto.getId());
        System.out.println("player id : " + dto.getId());

        playerRanking.setScoredGoals(dto.getScoredGoal());

        PlayingTime playingTime = dto.getPlayingTime();

        if (playingTime != null) {
            playingTime.convertToSeconds();
        }

        playerRanking.setPlayingTime(playingTime);

        return playerRanking;
    }
}
