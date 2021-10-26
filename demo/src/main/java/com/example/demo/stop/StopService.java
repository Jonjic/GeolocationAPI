package com.example.demo.stop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StopService {
    StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public void addNewStop(Stop stop){
        stopRepository.save(stop);
    }

    public List <Stop> getStopsEagerly(){
        List <Stop> stopList = stopRepository.findAllStopsEagerly();
        if(stopList.isEmpty()){
            throw new IllegalStateException("There are no stops in database");
        }
        else {
            return stopList;
        }
    }

    public List <Stop> getStopsOnStationBetween(Long busId, Long stationId, LocalDateTime firstDate, LocalDateTime secondDate){
       return stopRepository.findStopsOnStationByIdBetween(busId, stationId, firstDate, secondDate);
    }

    public List <Stop> getAllBusStopsByIdSince(Long busId, LocalDateTime beginDate) {
        List <Stop> stopList = stopRepository.findAllBusStopsByIdSince(busId, beginDate);;
        if(stopList.isEmpty()){
            throw new IllegalStateException("There are no stops in database for starting date " + beginDate.toString());
        }
        else {
            return stopList;
        }
    }

    public List <Stop> getAllBusStopsByIdBetween(Long busId, LocalDateTime beginDate, LocalDateTime endDate) {
        List <Stop> stopList = stopRepository.findAllBusStopsByIdBetween(busId, beginDate, endDate);;
        if(stopList.isEmpty()){
            throw new IllegalStateException("There are no stops that match those search parameters");
        }
        else {
            return stopList;
        }
    }

    public List<Object[]> getAllBusStations(Long busId){
        List<Object[]> objects = stopRepository.findAllBusStations(busId);
        if (objects.isEmpty()){
            throw new IllegalStateException("There are not enough bus stops in database to aquire this information");
        }
        else{
            return objects;
        }
    }
}
