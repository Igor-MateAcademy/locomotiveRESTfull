package com.ua.locomotive.services;

import com.ua.locomotive.entities.DriverEntity;
import com.ua.locomotive.exceptions.DriverNotFoundException;

/**
 * Stripped service of DriverService for only 'GET' requests
 */
public interface DriverStrippedService {
    /**
     * Find driver by id
     * @param id driver's id to find
     * @return driver have been found
     * @throws DriverNotFoundException when cannot find driver
     */
    DriverEntity findById(Long id) throws DriverNotFoundException;
}
