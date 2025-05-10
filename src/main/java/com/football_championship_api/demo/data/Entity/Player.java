package com.football_championship_api.demo.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private UUID id;
    private String name;
    private int number;
    private PlayerPosition position;
    private String nationality;
    private int age;
    private Club currentClub;

    public Player(UUID id, String name, int number, PlayerPosition position, String nationality, int age) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
    }
}
