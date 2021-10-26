package com.example.demo.stop;

import com.example.demo.bus.Bus;
import com.example.demo.station.Station;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Stop {

    @Id
    @SequenceGenerator(
            name = "stop_sequence_generator",
            sequenceName = "stop_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stop_sequence_generator"

    )
    private Long id;
    private LocalDateTime timestamp;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    public Stop() {}

    public Stop(Long id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Stop(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Bus getBus() {
        return bus;
    }

    public Station getStation() {
        return station;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void addStop(Station station, Bus bus){
        station.getStops().add(this);
        bus.getStops().add(this);
        this.station = station;
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
