package com.example.demo.business;

import java.time.LocalDateTime;

public class GpsTimeBetweenStationsDTO {
    private Long busId;
    private Long beginStationId;
    private Long endStationId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public Long getBeginStationId() {
        return beginStationId;
    }

    public void setBeginStationId(Long beginStationId) {
        this.beginStationId = beginStationId;
    }

    public Long getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(Long endStationId) {
        this.endStationId = endStationId;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
