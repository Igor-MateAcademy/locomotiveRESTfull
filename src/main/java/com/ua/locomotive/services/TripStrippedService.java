package com.ua.locomotive.services;

import com.ua.locomotive.entities.TripEntity;

import java.util.List;

public interface TripStrippedService {
    List<TripEntity> findAll();
}
