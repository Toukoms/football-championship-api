package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.entity.Coach;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClubDTO {
    private UUID id;
    private String name;
    private String acronym;
    private LocalDateTime yearCreation;
    private String stadium;
    private Coach coach;
}
