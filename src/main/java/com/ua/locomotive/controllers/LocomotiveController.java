package com.ua.locomotive.controllers;

import com.ua.locomotive.entities.LocomotiveEntity;
import com.ua.locomotive.exceptions.DriverNotFoundException;
import com.ua.locomotive.exceptions.LocomotiveInTransitException;
import com.ua.locomotive.exceptions.LocomotiveNotFoundException;
import com.ua.locomotive.services.LocomotiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/locomotives")
@CrossOrigin(origins = "http://localhost:3000")
public class LocomotiveController {

    private final LocomotiveService locomotiveService;

    @Autowired
    public LocomotiveController(LocomotiveService locomotiveService) {
        this.locomotiveService = locomotiveService;
    }

    @GetMapping
    public ResponseEntity<List<LocomotiveEntity>> findAll() {
        List<LocomotiveEntity> locomotives = locomotiveService.findAll();
        return new ResponseEntity<>(locomotives, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<LocomotiveEntity>> findAllFree() {
        List<LocomotiveEntity> locomotivesFree = locomotiveService.findAllFree();
        return new ResponseEntity<>(locomotivesFree, HttpStatus.OK);
    }

    @GetMapping("/busy")
    public ResponseEntity<List<LocomotiveEntity>> findAllBusy() {
        List<LocomotiveEntity> locomotivesBusy = locomotiveService.findAllBusy();
        return new ResponseEntity<>(locomotivesBusy, HttpStatus.OK);
    }

    @GetMapping("/driver")
    public ResponseEntity<?> findAllByDriver(@RequestParam("driver_id") Long driver_id) {
        try {
            Set<LocomotiveEntity> locomotives = locomotiveService.findAllByDriver(driver_id);
            return new ResponseEntity<>(locomotives, HttpStatus.OK);
        } catch (DriverNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<LocomotiveEntity> save(@RequestBody LocomotiveEntity locomotive) {
        LocomotiveEntity locomotiveSaved = locomotiveService.save(locomotive);
        return locomotiveSaved != null
                ? new ResponseEntity<>(locomotive, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam("locomotive_id") Long locomotive_id,
                                    @RequestBody LocomotiveEntity locomotive) {
        try {
            LocomotiveEntity locomotiveUpdated = locomotiveService.update(locomotive_id, locomotive);
            return new ResponseEntity<>(locomotiveUpdated, HttpStatus.OK);
        } catch (LocomotiveNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("locomotive_id") Long locomotive_id) {
        try {
            locomotiveService.delete(locomotive_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LocomotiveNotFoundException | LocomotiveInTransitException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

}
