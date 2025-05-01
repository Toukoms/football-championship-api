package com.football_championship_api.demo.data.Controller;


import com.football_championship_api.demo.data.DTO.ClubDTO;
import com.football_championship_api.demo.data.Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bestClubs")
public class ClubController {

    @Autowired
    ClubService service;

    @GetMapping
    public ResponseEntity<List<ClubDTO>> getBestClubs(){
        List<ClubDTO> clubs = service.getBestClubs();
        return ResponseEntity.ok(clubs);
    }
}
