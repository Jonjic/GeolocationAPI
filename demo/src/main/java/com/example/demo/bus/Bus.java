package com.example.demo.bus;

import com.example.demo.action.Action;
import com.example.demo.stop.Stop;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Bus{
    @Id
    @SequenceGenerator(
            name = "bus_sequence_generator",
            sequenceName = "bus_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bus_sequence"
    )
    @Column(name = "bus_id")
    private long id;
    private long number;
    private String model;
    private String registration;

    @JsonIgnore
    @OneToMany(mappedBy = "bus")
    private List <Action> actions = new ArrayList<>();

    @JsonIgnore
    @OneToMany (mappedBy = "bus")
    private Set<Stop> stops = new HashSet<>();

    public Bus(){};

    public Bus(long id, long number, String model, String registration) {
        this.id = id;
        this.number = number;
        this.model = model;
        this.registration = registration;
    }

    //No ID constructor because database will generate its own
    public Bus(long number, String model, String registration) {
        this.number = number;
        this.model = model;
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Set<Stop> getStops() {
        return stops;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", number=" + number +
                ", model='" + model + '\'' +
                ", registration='" + registration + '\'' +
                '}';
    }
}
