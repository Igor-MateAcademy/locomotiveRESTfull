package com.ua.locomotive.services;

import com.ua.locomotive.entities.TripEntity;
import com.ua.locomotive.exceptions.TripNotFoundException;

import java.util.List;

public interface TripService {

    /**
     * Find trip by id
     * @param id trip's id to find
     * @return trip have been found
     * @throws TripNotFoundException when cannot find trip
     */
    TripEntity findById(Long id) throws TripNotFoundException;

    /**
     * Find all trips
     * @return list of trips
     */
    List<TripEntity> findAll();

    /**
     * Find all trips which have been ended;
     * @return list of trips
     */
    List<TripEntity> findAllEnded();

    /**
     * Find all trips which have no end yet;
     * @return list of trips
     */
    List<TripEntity> findAllNotEnded();

    /**
     * Save trip to database
     * @param locomotive_id locomotive's id to set
     * @param driver_id driver's id to set
     * @param startDate date and time to start trip
     * @param endDate date and time to end trip
     * @param trip trip to save
     * @return trip have been saved
     */
    TripEntity save(Long locomotive_id, Long driver_id, String startDate,
                    String endDate, TripEntity trip) throws Exception;

    /**
     * Update trip in database
     * @param id trip's id to update
     * @param locomotive_id locomotive's id to set
     * @param driver_id driver's id to set
     * @param startDate date and time to start trip
     * @param endDate date and time to end trip
     * @param trip new trip to update
     * @return trip have been updated
     * @throws TripNotFoundException when cannot find trip
     */
    TripEntity update(Long id, Long locomotive_id, Long driver_id, String startDate,
                      String endDate, TripEntity trip) throws Exception;

    /**
     * Delete trip from database
     * @param id trip's id to delete
     * @throws TripNotFoundException when cannot find trip to delete
     */
    void delete(Long id) throws TripNotFoundException;
}
