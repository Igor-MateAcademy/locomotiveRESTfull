package com.ua.locomotive.services.impl;

import com.ua.locomotive.entities.DriverEntity;
import com.ua.locomotive.entities.LocomotiveEntity;
import com.ua.locomotive.entities.TripEntity;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.exceptions.LocomotiveNotFoundException;
import com.ua.locomotive.exceptions.TripNotFoundException;
import com.ua.locomotive.reposirories.TripRepository;
import com.ua.locomotive.services.DriverStrippedService;
import com.ua.locomotive.services.LocomotiveStrippedService;
import com.ua.locomotive.services.TripService;
import com.ua.locomotive.services.TripStrippedService;
import com.ua.locomotive.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService, TripStrippedService {

    private final TripRepository tripRepository;
    private final DriverStrippedService driverService;
    private final LocomotiveStrippedService locomotiveService;
    private final DateService dateService;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, DriverStrippedService driverService,
                           LocomotiveStrippedService locomotiveService, DateService dateService) {
        this.tripRepository = tripRepository;
        this.driverService = driverService;
        this.locomotiveService = locomotiveService;
        this.dateService = dateService;
    }

    @Override
    public TripEntity findById(Long id) throws TripNotFoundException {
        return tripRepository.findById(id)
                .orElseThrow(
                        () -> new TripNotFoundException("Cannot find trip")
                );
    }

    @Override
    public List<TripEntity> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public List<TripEntity> findAllEnded() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return tripRepository.findAll()
                .stream()
                .filter(t -> t.getEndDate().compareTo(currentTimestamp) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripEntity> findAllNotEnded() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return tripRepository.findAll()
                .stream()
                .filter(t -> t.getEndDate().compareTo(currentTimestamp) >= 0)
                .collect(Collectors.toList());
    }

    @Override
    public TripEntity save(Long locomotive_id, Long driver_id,
                           String startDate, String endDate,
                           TripEntity trip) throws LocomotiveNotFoundException, DriverNotFoundException {

        LocomotiveEntity locomotive = locomotiveService.findById(locomotive_id);
        DriverEntity driver = driverService.findById(driver_id);

        Timestamp startTimestamp = dateService.parseToTimestamp(startDate);
        Timestamp endTimestamp = dateService.parseToTimestamp(endDate);

        trip.setStartDate(startTimestamp);
        trip.setEndDate(endTimestamp);

        trip.setLocomotive(locomotive);
        trip.setDriver(driver);

        return tripRepository.save(trip);
    }

    @Override
    public TripEntity update(Long id, Long locomotive_id, Long driver_id,
                             String startDate, String endDate,
                             TripEntity trip) throws TripNotFoundException,
            LocomotiveNotFoundException, DriverNotFoundException {

        TripEntity tripToUpdate = findById(id);

        LocomotiveEntity locomotive = locomotiveService.findById(locomotive_id);
        DriverEntity driver = driverService.findById(driver_id);

        Timestamp startTimestamp = dateService.parseToTimestamp(startDate);
        Timestamp endTimestamp = dateService.parseToTimestamp(endDate);

        tripToUpdate.setLocomotive(locomotive);
        tripToUpdate.setDriver(driver);

        tripToUpdate.setStartDate(startTimestamp);
        tripToUpdate.setEndDate(endTimestamp);

        tripToUpdate.setStartFuelLevel(trip.getStartFuelLevel());
        tripToUpdate.setEndFuelLevel(trip.getEndFuelLevel());

        tripToUpdate.setBeginning(trip.getBeginning());
        tripToUpdate.setDestination(trip.getDestination());
        tripToUpdate.setExitPoint(trip.getExitPoint());

        tripToUpdate.setTrainType(trip.getTrainType());

        return tripRepository.save(tripToUpdate);
    }

    @Override
    public void delete(Long id) throws TripNotFoundException {
        TripEntity tripToDelete = findById(id);
        tripRepository.delete(tripToDelete);
    }
}
