package com.ua.locomotive.controllers;

import com.ua.locomotive.exceptions.DriverInTransitException;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.entities.DriverEntity;
import com.ua.locomotive.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@CrossOrigin(origins = "http://localhost:3000")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<DriverEntity>> findAll() {
        List<DriverEntity> drivers = driverService.findAll();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<DriverEntity>> findAllFree() {
        List<DriverEntity> driversFree = driverService.findAllFree();
        return new ResponseEntity<>(driversFree, HttpStatus.OK);
    }

    @GetMapping("/busy")
    public ResponseEntity<List<DriverEntity>> findAllBusy() {
        List<DriverEntity> driversBusy = driverService.findAllBusy();
        return new ResponseEntity<>(driversBusy, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DriverEntity> save(@RequestBody DriverEntity driver) {
        DriverEntity driverSaved = driverService.save(driver);
        return driverSaved != null
                ? new ResponseEntity<>(driverSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam("driver_id") Long driver_id,
                                    @RequestBody DriverEntity driver) {
        try {
            DriverEntity driverUpdated = driverService.update(driver_id, driver);
            return new ResponseEntity<>(driverUpdated, HttpStatus.OK);
        } catch (DriverNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("driver_id") Long driver_id) {
        try {
            driverService.delete(driver_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DriverNotFoundException | DriverInTransitException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
