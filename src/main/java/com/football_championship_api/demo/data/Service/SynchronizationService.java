package com.football_championship_api.demo.data.Service;


import com.football_championship_api.demo.data.Client.ExternalApiClient;
import com.football_championship_api.demo.data.DTO.ClubDTO;
import com.football_championship_api.demo.data.DTO.ClubStatDTO;
import com.football_championship_api.demo.data.DTO.PlayerDTO;
import com.football_championship_api.demo.data.DTO.PlayerStatDTO;
import com.football_championship_api.demo.data.Mapper.ClubMapper;
import com.football_championship_api.demo.data.Mapper.ClubStatisticsMapper;
import com.football_championship_api.demo.data.Mapper.PlayerMapper;
import com.football_championship_api.demo.data.Mapper.PlayerStatisticsMapper;
import com.football_championship_api.demo.data.entity.Club;
import com.football_championship_api.demo.data.entity.ClubRanking;
import com.football_championship_api.demo.data.entity.Player;
import com.football_championship_api.demo.data.entity.PlayerRanking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SynchronizationService {

    private final ExternalApiClient apiClient;
    private final PlayerMapper playerMapper;
    private final ClubMapper clubMapper;
    private final ClubStatisticsMapper clubStatMapper;
    private final PlayerStatisticsMapper playerStatMapper;

    private final PlayerDAO playerDAO;
    private final ClubDAO clubDAO;
    private final ClubRankingDAO clubRankingDAO;
    private final pLayerRankingDAO playerRankingDAO;


    public void syncAllData() {

        List<PlayerDTO> playerDTOs = apiClient.fetchPlayers();
        List<Player> players = playerDTOs.stream().map(playerMapper::toEntity).toList();
        players.forEach(playerDAO::insertAll);


        List<ClubDTO> clubDTOs = apiClient.fetchClubs();
        List<Club> clubs = clubDTOs.stream().map(clubMapper::toEntity).toList();
        clubs.forEach(clubDAO::insertAll);


        List<ClubStatDTO> clubStatDTOs = apiClient.fetchClubStat(int seasonYear);
        List<ClubRanking> clubRankings = clubStatDTOs.stream().map(clubStatMapper::toEntity).toList();
        rankings.forEach(clubRankingDAO::insertAll);

        List<PlayerStatDTO> playerStatDTOs = apiClient.fetchPlayerStat();
        List<PlayerRanking> playerRankings = playerStatDTOs.stream().map(playerStatMapper::toEntity).toList();
        rankings.forEach(clubRankingDAO::insertAll);
    }

}
