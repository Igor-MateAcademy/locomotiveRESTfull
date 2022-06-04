package com.ua.locomotive.entities;

import com.ua.locomotive.model.TrainType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "trips")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "locomotive_id")
    private LocomotiveEntity locomotive;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driverEntity;

    private Timestamp startDate;
    private Timestamp endDate;

    private Double startFuelLevel;
    private Double endFuelLevel;

    private String destination;

    @Enumerated(EnumType.STRING)
    private TrainType trainType;

    private String exitPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocomotiveEntity getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(LocomotiveEntity locomotive) {
        this.locomotive = locomotive;
    }

    public DriverEntity getDriver() {
        return driverEntity;
    }

    public void setDriver(DriverEntity driverEntity) {
        this.driverEntity = driverEntity;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Double getStartFuelLevel() {
        return startFuelLevel;
    }

    public void setStartFuelLevel(Double startFuelLevel) {
        this.startFuelLevel = startFuelLevel;
    }

    public Double getEndFuelLevel() {
        return endFuelLevel;
    }

    public void setEndFuelLevel(Double endFuelLevel) {
        this.endFuelLevel = endFuelLevel;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

    public String getExitPoint() {
        return exitPoint;
    }

    public void setExitPoint(String exitPoint) {
        this.exitPoint = exitPoint;
    }
}
