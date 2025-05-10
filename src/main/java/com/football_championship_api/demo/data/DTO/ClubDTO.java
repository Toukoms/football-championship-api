package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.Entity.Coach;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubDTO {
    private UUID id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private Coach coach;
}
