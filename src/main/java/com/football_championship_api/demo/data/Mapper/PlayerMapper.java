package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.PlayerDTO;
import com.football_championship_api.demo.data.entity.Player;

public class PlayerMapper implements Mapper<Player, PlayerDTO> {

    @Override
    public Player toEntity(PlayerDTO dto) {
        Player player = new Player();

        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setNumber(dto.getNumber());
        player.setPosition(dto.getPosition());
        player.setNationality(dto.getNationality());
        player.setAge(dto.getAge());

        return player;
    }
}
