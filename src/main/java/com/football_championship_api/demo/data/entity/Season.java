package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    private UUID id;
    private int year;
    private String alias;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
