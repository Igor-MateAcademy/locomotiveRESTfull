package com.ua.locomotive.reposirories;

import com.ua.locomotive.entities.DriverEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<DriverEntity, Long> {
    List<DriverEntity> findAll();
}
