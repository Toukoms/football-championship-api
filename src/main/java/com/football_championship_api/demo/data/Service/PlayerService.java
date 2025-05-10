package com.football_championship_api.demo.data.Service;

import com.football_championship_api.demo.data.DTO.PlayerRankingDTO;
import com.football_championship_api.demo.data.Mapper.PlayerRankingMapper;
import com.football_championship_api.demo.data.Repository.PlayerDAO;
import com.football_championship_api.demo.data.Entity.PlayerRanking;
import com.football_championship_api.demo.data.Repository.PlayerRankingDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRankingDAO playerRankingDAO;
    private final PlayerRankingMapper mapper;

    public List<PlayerRankingDTO> getBestPlayers(@RequestParam(defaultValue = "5") int top, @RequestParam String playingTimeUnit){
        List<PlayerRanking> ranking = playerRankingDAO.findBestPlayers(top, playingTimeUnit);

        return ranking.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }



}
