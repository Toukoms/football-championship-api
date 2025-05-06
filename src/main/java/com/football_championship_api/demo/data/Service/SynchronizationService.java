package com.football_championship_api.demo.data.Service;


import com.football_championship_api.demo.data.Client.ExternalApiClient;
import com.football_championship_api.demo.data.DTO.*;
import com.football_championship_api.demo.data.Mapper.*;
import com.football_championship_api.demo.data.Repository.ClubDAO;
import com.football_championship_api.demo.data.Repository.ClubRankingDAO;
import com.football_championship_api.demo.data.Repository.PlayerDAO;
import com.football_championship_api.demo.data.Repository.PlayerRankingDAO;
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
    private final SeasonMapper seasonMapper;

    private final PlayerDAO playerDAO;
    private final ClubDAO clubDAO;
    private final ClubRankingDAO clubRankingDAO;
    private final PlayerRankingDAO playerRankingDAO;


    public void syncAllData() {

        List<SeasonDTO> seasonDTOs = apiClient.fetchSeasons();
        List<Integer> seasonYears = seasonDTOs.stream().map(seasonMapper::getSeasonYear).toList();


        List<PlayerDTO> playerDTOs = apiClient.fetchPlayers();
        List<Player> players = playerDTOs.stream().map(playerMapper::toEntity).toList();
        players.forEach(playerDAO::insert);

        List<ClubDTO> clubDTOs = apiClient.fetchClubs();
        List<Club> clubs = clubDTOs.stream().map(clubMapper::toEntity).toList();
        clubs.forEach(clubDAO::insert);

    }
    public void getStatistics(List<Integer> seasonYears){

        for (Integer seasonYear : seasonYears ){

            List<ClubStatDTO> clubStatDTOs = apiClient.fetchClubStat(seasonYear);
            List<ClubRanking> clubRankings = clubStatDTOs.stream().map(clubStatMapper::toEntity).toList();
            clubRankings.forEach(clubRankingDAO::insert);

            List<PlayerStatDTO> playerStatDTOs = apiClient.fetchPlayerStat(seasonYear);
            List<PlayerRanking> playerRankings = playerStatDTOs.stream().map(playerStatMapper::toEntity).toList();
            playerRankings.forEach(playerRankingDAO::insert);

        }
    }

}
