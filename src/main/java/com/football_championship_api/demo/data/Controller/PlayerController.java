package com.football_championship_api.demo.data.Controller;


import com.football_championship_api.demo.data.DTO.PlayerDTO;
import com.football_championship_api.demo.data.Service.PlayerService;
import com.football_championship_api.demo.data.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bestPlayers")
public class PlayerController {

    @Autowired
    PlayerService service;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getBestPlayers(){
        List<PlayerDTO> players = service.getBestPlayers();
        return ResponseEntity.ok(players);
    }

}
