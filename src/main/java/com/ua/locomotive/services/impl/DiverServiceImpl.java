package com.ua.locomotive.services.impl;

import com.ua.locomotive.exceptions.DriverInTransitException;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.entities.DriverEntity;
import com.ua.locomotive.reposirories.DriverRepository;
import com.ua.locomotive.services.DriverService;
import com.ua.locomotive.services.DriverStrippedService;
import com.ua.locomotive.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiverServiceImpl implements DriverService, DriverStrippedService {

    private final DriverRepository driverRepository;
    private final DateService dateService;

    @Autowired
    public DiverServiceImpl(DriverRepository driverRepository, DateService dateService) {
        this.driverRepository = driverRepository;
        this.dateService = dateService;
    }

    @Override
    public DriverEntity findById(Long id) throws DriverNotFoundException {
        return driverRepository.findById(id)
                .orElseThrow(
                        () -> new DriverNotFoundException("Cannot find driver")
                );
    }

    @Override
    public List<DriverEntity> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public List<DriverEntity> findAllFree() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return driverRepository.findAll()
                .stream()
                .filter(d -> d.getTrips().isEmpty() || d.getTrips().stream()
                        .anyMatch(t -> t.getEndDate().compareTo(currentTimestamp) < 0))
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverEntity> findAllBusy() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return driverRepository.findAll()
                .stream()
                .filter(d -> d.getTrips().stream()
                        .anyMatch(t -> t.getEndDate().compareTo(currentTimestamp) >= 0))
                .collect(Collectors.toList());
    }

    @Override
    public DriverEntity save(DriverEntity driverEntity) {
        return driverRepository.save(driverEntity);
    }

    @Override
    public DriverEntity update(Long id, DriverEntity driverEntity) throws DriverNotFoundException {
        DriverEntity driverToUpdate = findById(id);

        driverToUpdate.setFirstName(driverEntity.getFirstName());
        driverToUpdate.setLastName(driverEntity.getLastName());
        driverToUpdate.setDriverClass(driverEntity.getDriverClass());
        driverToUpdate.setTrips(driverEntity.getTrips());

        return driverRepository.save(driverToUpdate);
    }

    @Override
    public void delete(Long id) throws DriverNotFoundException, DriverInTransitException {
        DriverEntity driverToDelete = findById(id);

        Timestamp currentTimestamp = dateService.getCurrentDate();

        if (driverToDelete.getTrips().stream().anyMatch(t -> t.getEndDate().compareTo(currentTimestamp) >= 0))
                throw new DriverInTransitException("Driver in transit");

        driverRepository.delete(driverToDelete);
    }
}
