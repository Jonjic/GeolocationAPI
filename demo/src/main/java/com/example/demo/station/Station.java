package com.example.demo.station;

import com.example.demo.stop.Stop;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Station {
    @Id
    @GeneratedValue
    private Long id;
    private Double latitude;
    private Double longitude;
    private String name;

    @JsonIgnore
    @OneToMany (mappedBy = "station")
    private Set <Stop> stops = new HashSet<>();

    public Station(){}

    public Station(Long id, Double latitude, Double longitude, String name, Set<Stop> stops) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.stops = stops;
    }

    public Station(Double latitude, Double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public Station(Double latitude, Double longitude, String name, Set<Stop> stops) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.stops = stops;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Stop> getStops() {
        return stops;
    }

}
