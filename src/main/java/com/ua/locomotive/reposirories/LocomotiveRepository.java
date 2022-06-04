package com.ua.locomotive.reposirories;

import com.ua.locomotive.entities.LocomotiveEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocomotiveRepository extends CrudRepository<LocomotiveEntity, Long> {
    List<LocomotiveEntity> findAll();
}
