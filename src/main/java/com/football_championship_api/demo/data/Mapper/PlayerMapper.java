package com.football_championship_api.demo.data.Mapper;

import com.football_championship_api.demo.data.DTO.PlayerDTO;
import com.football_championship_api.demo.data.Entity.Club;
import com.football_championship_api.demo.data.Entity.Coach;
import com.football_championship_api.demo.data.Entity.Player;
import com.football_championship_api.demo.data.Mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
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

        if (dto.getCurrentClub() != null) {
            Club club = new Club();
            club.setId(dto.getCurrentClub().getId());
            club.setName(dto.getCurrentClub().getName());
            club.setAcronym(dto.getCurrentClub().getAcronym());
            club.setStadium(dto.getCurrentClub().getStadium());
            club.setYearCreation(dto.getCurrentClub().getYearCreation());



            if (dto.getCurrentClub().getCoach() != null) {
                Coach coach = new Coach();
                coach.setId(dto.getCurrentClub().getCoach().getId());
                coach.setName(dto.getCurrentClub().getCoach().getName());
                coach.setNationality(dto.getCurrentClub().getCoach().getNationality());
                coach.setCreatedAt(dto.getCurrentClub().getCoach().getCreatedAt());
                club.setCoach(coach);
            }

            player.setCurrentClub(club);
        }

        return player;
    }

    public PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();

        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setNumber(player.getNumber());
        dto.setPosition(player.getPosition());
        dto.setNationality(player.getNationality());
        dto.setAge(player.getAge());

        if (player.getCurrentClub() != null) {
            dto.setCurrentClub(new Club(
                    player.getCurrentClub().getId(),
                    player.getCurrentClub().getName(),
                    player.getCurrentClub().getAcronym(),
                    player.getCurrentClub().getYearCreation(),
                    player.getCurrentClub().getStadium(),
                    player.getCurrentClub().getCoach()
            ));
        }

        return dto;
    }
}
