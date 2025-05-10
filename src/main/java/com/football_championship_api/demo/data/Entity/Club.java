package com.football_championship_api.demo.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
