package com.football_championship_api.demo.data.repository;

import com.football_championship_api.demo.data.entity.PlayerEntity;
import com.football_championship_api.demo.data.repository.Filter;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAll(Filter filter);

    T save(T entity);
    List<T> saveAll(List<T> entities);
    void deleteById(ID id);
    boolean existsById(ID id);
}