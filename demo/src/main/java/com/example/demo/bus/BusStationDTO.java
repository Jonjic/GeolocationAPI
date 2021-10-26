package com.example.demo.bus;

import java.time.LocalDateTime;

public class BusStationDTO {
    private Long count;
    private LocalDateTime aquiredOn;
    private Long bus_id;
    private Long station_id;

    public Long getCount() {
        return count;
    }

    public LocalDateTime getAquiredOn() {
        return aquiredOn;
    }

    public Long getBus_id() {
        return bus_id;
    }

    public Long getStation_id() {
        return station_id;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setAquiredOn(LocalDateTime aquiredOn) {
        this.aquiredOn = aquiredOn;
    }

    public void setBus_id(Long bus_id) {
        this.bus_id = bus_id;
    }

    public void setStation_id(Long station_id) {
        this.station_id = station_id;
    }
}
