package com.football_championship_api.demo.data.Service;

import com.football_championship_api.demo.data.Client.ExternalApiClient;
import com.football_championship_api.demo.data.DTO.*;
import com.football_championship_api.demo.data.Mapper.*;
import com.football_championship_api.demo.data.Repository.ClubDAO;
import com.football_championship_api.demo.data.Repository.ClubRankingDAO;
import com.football_championship_api.demo.data.Repository.PlayerDAO;
import com.football_championship_api.demo.data.Repository.PlayerRankingDAO;
import com.football_championship_api.demo.data.Entity.Club;
import com.football_championship_api.demo.data.Entity.ClubRanking;
import com.football_championship_api.demo.data.Entity.Player;
import com.football_championship_api.demo.data.Entity.PlayerRanking;
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
        List<Integer> seasonYears = seasonDTOs.stream()
                .map(seasonMapper::getSeasonYear)
                .toList();


        System.out.println("Season Years: " + seasonYears);

        List<ClubDTO> clubDTOs = apiClient.fetchClubs();
        List<Club> clubs = clubDTOs.stream()
                .map(clubMapper::toEntity)
                .filter(club -> club.getId() != null && club.getName() != null)
                .toList();
        clubs.forEach(clubDAO::insert);

        List<PlayerDTO> playerDTOs = apiClient.fetchPlayers();
        List<Player> players = playerDTOs.stream()
                .map(playerMapper::toEntity)
                .filter(player -> player.getId() != null && player.getName() != null)
                .toList();
        players.forEach(playerDAO::insert);

        getStatistics(seasonYears);
    }

    public void getStatistics(List<Integer> seasonYears) {
        for (Integer seasonYear : seasonYears) {
            try {
                System.out.println("Season Year: " + seasonYear);

                List<ClubStatDTO> clubStatDTOs = apiClient.fetchClubStat(seasonYear);
                System.out.println("Club Stats for " + seasonYear + ": " + clubStatDTOs);
                if (clubStatDTOs.isEmpty()) {
                    System.out.println("No Club Stats for season " + seasonYear);
                }

                List<ClubRanking> clubRankings = clubStatDTOs.stream()
                        .map(clubStatMapper::toEntity)
                        .filter(cr -> cr != null && cr.getClub().getId() != null)
                        .toList();
                clubRankings.forEach(clubRankingDAO::insert);

                List<PlayerStatDTO> playerStatDTOs = apiClient.fetchPlayerStat(seasonYear);
                System.out.println("Player Stats for " + seasonYear + ": " + playerStatDTOs);
                if (playerStatDTOs.isEmpty()) {
                    System.out.println("No Player Stats for season " + seasonYear);
                }

                List<PlayerRanking> playerRankings = playerStatDTOs.stream()
                        .map(playerStatMapper::toEntity)
                        .peek(pr -> {
                            if (pr == null) {
                                System.out.println("Mapped is null");
                            } else {
                                System.out.println("Mapped.getPlayer(): " + pr.getPlayer());
                            }
                        })
                        .filter(pr -> pr != null && pr.getPlayer() != null)
                        .toList();

                playerRankings.forEach(playerRankingDAO::insert);
                System.out.println("Player inserted  found " + seasonYear + ": " + playerRankings);

            } catch (Exception e) {
                System.out.println("Error processing season year " + seasonYear + ": " + e.getMessage());
            }
        }
    }

}
