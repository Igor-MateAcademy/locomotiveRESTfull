package com.ua.locomotive.reposirories;

import com.ua.locomotive.entities.TripEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<TripEntity, Long> {
    List<TripEntity> findAll();
}
