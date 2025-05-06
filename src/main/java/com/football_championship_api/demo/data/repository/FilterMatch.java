package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.data.entity.PlayingStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterMatch {
    private PlayingStatus matchStatus;
    private String clubPlayingName;
    private LocalDate matchAfter;
    private LocalDate matchBeforeOrEquals;
}
