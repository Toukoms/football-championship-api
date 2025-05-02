package com.football_championship_api.demo.data.Client;


import com.football_championship_api.demo.data.DTO.ClubDTO;
import com.football_championship_api.demo.data.DTO.ClubStatDTO;
import com.football_championship_api.demo.data.DTO.PlayerDTO;
import com.football_championship_api.demo.data.DTO.PlayerStatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<PlayerDTO> fetchPlayers() {
        String url = "https://localhost:8080/players";
        ResponseEntity<PlayerDTO[]> response = restTemplate.getForEntity(url, PlayerDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public List<ClubDTO> fetchClubs() {
        String url = "https://localhost:8080/clubs";
        ResponseEntity<ClubDTO[]> response = restTemplate.getForEntity(url, ClubDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public List<ClubStatDTO> fetchClubStat( int seasonYear) {
        String url = "https://localhost:8080/clubs/statistics/{seasonYear}";
        ResponseEntity<ClubStatDTO[]> response = restTemplate.getForEntity(url, ClubStatDTO[].class, seasonYear);
        return Arrays.asList(response.getBody());
    }

    public List<PlayerStatDTO> fetchPlayerStat(int seasonYear) {
        String url = "https://localhost:8080/players/statistics/{seasonYear}";
        ResponseEntity<PlayerStatDTO[]> response = restTemplate.getForEntity(url, PlayerStatDTO[].class, seasonYear);
        return Arrays.asList(response.getBody());
    }
}
