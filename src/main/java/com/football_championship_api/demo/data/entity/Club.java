package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private UUID id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private Coach coach;
}
