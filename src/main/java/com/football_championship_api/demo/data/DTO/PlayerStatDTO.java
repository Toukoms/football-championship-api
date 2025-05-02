package com.football_championship_api.demo.data.DTO;

import com.football_championship_api.demo.data.entity.PlayingTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlayerStatDTO {
    private UUID id;
    private int scoredGoal;
    private PlayingTime playingTime;
}
