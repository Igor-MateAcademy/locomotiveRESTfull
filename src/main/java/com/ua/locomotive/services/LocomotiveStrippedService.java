package com.ua.locomotive.services;

import com.ua.locomotive.entities.LocomotiveEntity;
import com.ua.locomotive.exceptions.LocomotiveNotFoundException;

/**
 * Stripped service of Locomotive Service for only 'GET' requests
 */
public interface LocomotiveStrippedService {
    /**
     * Find locomotive by id
     * @param id locomotive's id to find
     * @return locomotive have been found
     * @throws LocomotiveNotFoundException when cannot find locomotive
     */
    LocomotiveEntity findById(Long id) throws LocomotiveNotFoundException;
}
