package com.example.demo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/gpsData")
public class GpsDataServiceController {
    GpsDataService gpsDataService;

    @Autowired
    public GpsDataServiceController(GpsDataService gpsDataService) {
        this.gpsDataService = gpsDataService;
    }

    @GetMapping(path = "/stationTime")
    public float getAverageTimeBetweenStations (@RequestBody GpsTimeBetweenStationsDTO dataTransferObject){
        return gpsDataService.getTimeBetweenStationsById(dataTransferObject);
    }

    @GetMapping(path = "/passengerCount")
    public List<TimespanPassengerReportDTO> getBusPassengerCountBetween (@RequestBody DateDTO dateDTO){
        return gpsDataService.TimespanPassengerReport(dateDTO);
    }
}
