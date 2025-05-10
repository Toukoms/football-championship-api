package com.football_championship_api.demo.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    private UUID id;
    private String name;
    private String nationality;
    private int createdAt;
}
