package com.example.demo.action;

import com.example.demo.bus.Bus;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Action {

    @Id
    @SequenceGenerator(
            name = "action_sequence_generator",
            sequenceName = "action_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "action_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    @Column(columnDefinition = "boolean default true")
    private  boolean entrance = true;

    public Action()
    {

    }

    public Action(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp =  LocalDateTime.now();
    }

    public Action(Double latitude, Double longitude, boolean entrance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp =  LocalDateTime.now();
        this.entrance = entrance;
    }

    public Action(Double latitude, Double longitude, LocalDateTime timestamp, boolean entrance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp =  timestamp;
        this.entrance = entrance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isEntrance() {
        return entrance;
    }

    public void setEntrance(boolean entrance) {
        this.entrance = entrance;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", entrance=" + entrance +
                ", bus=" + bus +
                ", user=" + user +
                '}';
    }
}
