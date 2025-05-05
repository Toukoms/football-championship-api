package com.football_championship_api.demo.data.repository;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilterPlayer {
    private String name;
    private Integer ageMinimum;
    private Integer ageMaximum;
    private String clubName;
}
