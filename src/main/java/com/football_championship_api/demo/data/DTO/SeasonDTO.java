package com.football_championship_api.demo.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeasonDTO {
    private int year;
    private String alias;
    private UUID id;
    private String status;
}
