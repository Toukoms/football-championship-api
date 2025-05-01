package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.entity.PlayerPosition;

import java.util.UUID;

public class PlayerDTO {
    private UUID id;
    private String name;
    private int number;
    private PlayerPosition position;
    private String nationality;
    private int age;
}
