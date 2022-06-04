package com.ua.locomotive.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.locomotive.model.DriverClass;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "drivers")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private DriverClass driverClass;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driverEntity")
    private List<TripEntity> trips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DriverClass getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(DriverClass driverClass) {
        this.driverClass = driverClass;
    }

    public List<TripEntity> getTrips() {
        return trips;
    }

    public void setTrips(List<TripEntity> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (!id.equals(that.id)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (driverClass != that.driverClass) return false;
        return Objects.equals(trips, that.trips);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + driverClass.hashCode();
        result = 31 * result + (trips != null ? trips.hashCode() : 0);
        return result;
    }
}
