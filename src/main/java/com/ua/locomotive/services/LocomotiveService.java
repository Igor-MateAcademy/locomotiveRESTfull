package com.ua.locomotive.services;

import com.ua.locomotive.entities.LocomotiveEntity;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.exceptions.LocomotiveInTransitException;
import com.ua.locomotive.exceptions.LocomotiveNotFoundException;

import java.util.List;
import java.util.Set;

public interface LocomotiveService {

    /**
     * Find locomotive by id
     * @param id locomotive's id to find
     * @return locomotive have been found
     * @throws LocomotiveNotFoundException when cannot find locomotive
     */
    LocomotiveEntity findById(Long id) throws LocomotiveNotFoundException;

    /**
     * Find all locomotives
     * @return list of locomotives
     */
    List<LocomotiveEntity> findAll();

    /**
     * Find all locomotives which are free
     * @return list of locomotives
     */
    List<LocomotiveEntity> findAllFree();

    /**
     * Find all locomotives which are busy
     * @return list of locomotives
     */
    List<LocomotiveEntity> findAllBusy();

    /**
     * Find all locomotives by driver
     * @param driver_id driver's id to find all locomotives
     * @return set of locomotives
     * @throws DriverNotFoundException when cannot find driver
     */
    Set<LocomotiveEntity> findAllByDriver(Long driver_id) throws DriverNotFoundException;

    /**
     * Save locomotive to database
     * @param locomotive locomotive to save
     * @return locomotive have been saved
     */
    LocomotiveEntity save(LocomotiveEntity locomotive);

    /**
     * Update locomotive in database
     * @param id locomotive's id to update
     * @param locomotive locomotive updated
     * @return locomotive have been updated
     * @throws LocomotiveNotFoundException when cannot find locomotive to update
     */
    LocomotiveEntity update(Long id, LocomotiveEntity locomotive) throws LocomotiveNotFoundException;

    /**
     * Delete locomotive from database
     * @param id locomotive's id to delete
     * @throws LocomotiveNotFoundException when cannot find locomotive
     * @throws LocomotiveInTransitException when locomotive in transit
     */
    void delete(Long id) throws LocomotiveNotFoundException, LocomotiveInTransitException;

}
