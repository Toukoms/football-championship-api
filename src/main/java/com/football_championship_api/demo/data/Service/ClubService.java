package com.football_championship_api.demo.data.Service;


import com.football_championship_api.demo.data.DTO.ClubRankingDTO;
import com.football_championship_api.demo.data.Entity.ClubRanking;
import com.football_championship_api.demo.data.Mapper.ClubRankingMapper;
import com.football_championship_api.demo.data.Repository.ClubDAO;
import com.football_championship_api.demo.data.Repository.ClubRankingDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClubService {
    private final ClubRankingDAO clubDAO;
    private final ClubRankingMapper mapper;

    public List<ClubRankingDTO> getBestClubs(int top){
        List<ClubRanking> ranking =  clubDAO.findBestClubs(top);

        return ranking.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
