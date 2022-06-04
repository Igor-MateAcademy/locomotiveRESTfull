package com.ua.locomotive.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.locomotive.model.LocomotiveType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "locomotives")
public class LocomotiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locomotive")
    private List<TripEntity> trips;

    @Enumerated(EnumType.STRING)
    private LocomotiveType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TripEntity> getTrips() {
        return trips;
    }

    public void setTrips(List<TripEntity> trips) {
        this.trips = trips;
    }

    public LocomotiveType getType() {
        return type;
    }

    public void setType(LocomotiveType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocomotiveEntity that = (LocomotiveEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!Objects.equals(trips, that.trips)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (trips != null ? trips.hashCode() : 0);
        result = 31 * result + type.hashCode();
        return result;
    }
}
