package com.football_championship_api.demo.data.Client;

import com.football_championship_api.demo.data.DTO.*;
import com.football_championship_api.demo.data.Entity.DurationUnit;
import com.football_championship_api.demo.data.Entity.PlayingTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExternalApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<PlayerDTO> fetchPlayers() {
        String url = "http://localhost:8080/players";
        ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity(url, PlayerDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public List<ClubDTO> fetchClubs() {
        String url = "http://localhost:8080/clubs";
        ResponseEntity<ClubDTO[]> response = restTemplate.getForEntity(url, ClubDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public List<ClubStatDTO> fetchClubStat(int seasonYear) {
        String url = "http://localhost:8080/clubs/statistics/{seasonYear}";
        try {
            ResponseEntity<ClubStatDTO[]> response = restTemplate.getForEntity(url, ClubStatDTO[].class, seasonYear);
            return response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (HttpClientErrorException.NotFound e) {
            return List.of();
        }
    }


    public List<PlayerStatDTO> fetchPlayerStat(int seasonYear) {
        List<PlayerDTO> players = fetchPlayers();
        List<PlayerStatDTO> allStats = new ArrayList<>();

        for (PlayerDTO player : players) {
            String statUrl = "http://localhost:8080/players/{id}/statistics/{seasonYear}";
            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(
                        statUrl,
                        Map.class,
                        player.getId().toString(),
                        seasonYear
                );

                if (response.getBody() != null) {
                    Map<String, Object> body = response.getBody();

                    Integer scoredGoals = (Integer) body.get("scoredGoals");
                    Number playingTimeValue = (Number) body.get("playingTimeValue");
                    String playingTimeUnitStr = (String) body.get("playingTimeUnit");

                    PlayingTime playingTime = new PlayingTime();
                    playingTime.setValue(playingTimeValue);
                    playingTime.setDurationUnit(DurationUnit.valueOf(playingTimeUnitStr));


                    PlayerStatDTO dto = new PlayerStatDTO();
                    dto.setId(player.getId());
                    dto.setScoredGoal(scoredGoals != null ? scoredGoals : 0);
                    dto.setPlayingTime(playingTime);

                    System.out.println("Stats found for player: " + player.getId());
                    allStats.add(dto);
                } else {
                    System.out.println("No stats for player: " + player.getId());
                }

            } catch (HttpClientErrorException.NotFound e) {
                System.out.println("404 Not Found for player: " + player.getId() + " in season " + seasonYear);
            } catch (Exception e) {
                System.out.println("Error fetching stats for player: " + player.getId());
                e.printStackTrace();
            }
        }

        return allStats;
    }





    public List<SeasonDTO> fetchSeasons() {
        String url = "http://localhost:8080/seasons";
        ResponseEntity<SeasonDTO[]> response = restTemplate.getForEntity(url, SeasonDTO[].class);

        if (response.getBody() != null) {
            for (SeasonDTO season : response.getBody()) {
                System.out.println("Season: " + season.getYear() + " alias " + season.getAlias());
            }
        } else {
            System.out.println("No seasons fetched");
        }

        return Arrays.asList(response.getBody());
    }


}
