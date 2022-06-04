package com.ua.locomotive.services.impl;

import com.ua.locomotive.entities.DriverEntity;
import com.ua.locomotive.entities.LocomotiveEntity;
import com.ua.locomotive.entities.TripEntity;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.exceptions.LocomotiveInTransitException;
import com.ua.locomotive.exceptions.LocomotiveNotFoundException;
import com.ua.locomotive.reposirories.LocomotiveRepository;
import com.ua.locomotive.services.DriverStrippedService;
import com.ua.locomotive.services.LocomotiveService;
import com.ua.locomotive.services.LocomotiveStrippedService;
import com.ua.locomotive.services.TripStrippedService;
import com.ua.locomotive.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocomotiveServiceImpl implements LocomotiveService, LocomotiveStrippedService {

    private final LocomotiveRepository locomotiveRepository;
    private final ApplicationContext context;
    private final DateService dateService;

    @Autowired
    public LocomotiveServiceImpl(LocomotiveRepository locomotiveRepository,
                                 ApplicationContext context, DateService dateService) {
        this.locomotiveRepository = locomotiveRepository;
        this.dateService = dateService;
        this.context = context;
    }


    @Override
    public LocomotiveEntity findById(Long id) throws LocomotiveNotFoundException {
        return locomotiveRepository.findById(id)
                .orElseThrow(
                        () -> new LocomotiveNotFoundException("Cannot find locomotive")
                );
    }

    @Override
    public List<LocomotiveEntity> findAll() {
        return locomotiveRepository.findAll();
    }

    @Override
    public List<LocomotiveEntity> findAllFree() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return locomotiveRepository.findAll()
                .stream()
                .filter(l -> l.getTrips().isEmpty() || l.getTrips().stream()
                        .anyMatch(t -> t.getEndDate().compareTo(currentTimestamp) < 0))
                .collect(Collectors.toList());
    }

    @Override
    public List<LocomotiveEntity> findAllBusy() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return locomotiveRepository.findAll()
                .stream()
                .filter(l -> l.getTrips().stream()
                        .anyMatch(t -> t.getEndDate().compareTo(currentTimestamp) >= 0))
                .collect(Collectors.toList());
    }

    @Override
    public LocomotiveEntity save(LocomotiveEntity locomotive) {
        return locomotiveRepository.save(locomotive);
    }

    @Override
    public LocomotiveEntity update(Long id, LocomotiveEntity locomotive) throws LocomotiveNotFoundException {
        LocomotiveEntity locomotiveToUpdate = findById(id);

        locomotiveToUpdate.setName(locomotive.getName());
        locomotiveToUpdate.setType(locomotive.getType());
        locomotive.setTrips(locomotive.getTrips());

        return locomotiveRepository.save(locomotiveToUpdate);
    }

    @Override
    public void delete(Long id) throws LocomotiveNotFoundException, LocomotiveInTransitException {
        LocomotiveEntity locomotiveToDelete = findById(id);

        Timestamp currentTimestamp = dateService.getCurrentDate();

        if (locomotiveToDelete.getTrips().stream().anyMatch(t -> t.getEndDate().compareTo(currentTimestamp) >= 0))
            throw new LocomotiveInTransitException("Locomotive in transit");

        locomotiveRepository.delete(locomotiveToDelete);
    }

    @Override
    public Set<LocomotiveEntity> findAllByDriver(Long driver_id) throws DriverNotFoundException {
        TripStrippedService tripService = context.getBean(TripServiceImpl.class, "tripServiceImpl");
        DriverStrippedService driverService = context.getBean(DiverServiceImpl.class, "diverServiceImpl");

        DriverEntity driver = driverService.findById(driver_id);
        return tripService.findAll()
                .stream()
                .filter(t -> t.getDriver().equals(driver))
                .map(TripEntity::getLocomotive)
                .collect(Collectors.toSet());
    }
}
