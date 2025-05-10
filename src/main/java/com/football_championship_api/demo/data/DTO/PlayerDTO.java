package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.Entity.Club;
import com.football_championship_api.demo.data.Entity.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlayerDTO {
    private UUID id;
    private String name;
    private int number;
    private PlayerPosition position;
    private String nationality;
    private int age;
    private Club currentClub;
}
