package com.ua.locomotive.controllers;

import com.ua.locomotive.entities.TripEntity;
import com.ua.locomotive.exceptions.TripNotFoundException;
import com.ua.locomotive.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public ResponseEntity<List<TripEntity>> findAll() {
        List<TripEntity> trips = tripService.findAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/intransit")
    public ResponseEntity<List<TripEntity>> findAllInTransit() {
        List<TripEntity> tripsInTransit = tripService.findAllNotEnded();
        return new ResponseEntity<>(tripsInTransit, HttpStatus.OK);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<TripEntity>> findAllFinished() {
        List<TripEntity> tripsFinished = tripService.findAllEnded();
        return new ResponseEntity<>(tripsFinished, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam("locomotive_id") Long locomotive_id,
                                  @RequestParam("driver_id") Long driver_id,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestBody TripEntity trip) {
        try {
            TripEntity tripSaved = tripService.save(locomotive_id, driver_id, startDate, endDate, trip);
            return new ResponseEntity<>(tripSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam("trip_id") Long trip_id,
                                    @RequestParam("locomotive_id") Long locomotive_id,
                                    @RequestParam("driver_id") Long driver_id,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate,
                                    @RequestBody TripEntity trip) {
        try {
            TripEntity tripUpdated = tripService.update(trip_id, locomotive_id, driver_id, startDate, endDate, trip);
            return new ResponseEntity<>(tripUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("trip_id") Long trip_id) {
        try {
            tripService.delete(trip_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TripNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
