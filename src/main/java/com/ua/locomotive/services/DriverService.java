package com.ua.locomotive.services;

import com.ua.locomotive.exceptions.DriverInTransitException;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.entities.DriverEntity;

import java.util.List;

public interface DriverService {
    /**
     * Find driver by id
     * @param id driver's id find to
     * @return driver have been found
     * @throws DriverNotFoundException when cannot find driver
     */
    DriverEntity findById(Long id) throws DriverNotFoundException;

    /**
     * Find all drivers
     * @return list of drivers
     */
    List<DriverEntity> findAll();

    /**
     * Find all drivers who have no any trip
     * @return list of driver
     */
    List<DriverEntity> findAllFree();

    /**
     * Find all drivers who have some trip
     * @return list of drivers
     */
    List<DriverEntity> findAllBusy();

    /**
     * Save driver to database
     * @param driverEntity driver to save
     * @return driver have been saved
     */
    DriverEntity save(DriverEntity driverEntity);

    /**
     * Update driver in database
     * @param id driver's id to update
     * @param driverEntity driver to update
     * @return driver have been updated
     * @throws DriverNotFoundException when cannot find driver
     */
    DriverEntity update(Long id, DriverEntity driverEntity) throws DriverNotFoundException;

    /**
     * Delete driver by id
     * @param id driver's id to delete
     * @throws DriverNotFoundException when cannot find driver to delete
     * @throws DriverInTransitException when driver in transit
     */
    void delete(Long id) throws DriverNotFoundException, DriverInTransitException;
}
