package com.football_championship_api.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {
    private UUID id;
    private String name;
    private Long number;
    private PlayerPosition position;
    private String nationality;
    private Long age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ClubEntity currentClub;
}
