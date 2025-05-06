package com.football_championship_api.demo.data.Mapper;

public interface Mapper<Entity,Dto> {
    Entity toEntity(Dto dto);
}
