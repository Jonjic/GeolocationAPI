package com.example.demo.business;

import com.example.demo.action.Action;
import com.example.demo.action.ActionService;
import com.example.demo.station.Station;
import com.example.demo.station.StationService;
import com.example.demo.stop.Stop;
import com.example.demo.stop.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.Math;

import java.sql.Array;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


//This will probably need to be async function in the future
@Service
public class GpsDataService {
    StationService stationService;
    StopService stopService;
    ActionService actionService;

    @Autowired
    public GpsDataService(StationService stationService, StopService stopService) {
        this.stationService = stationService;
        this.stopService = stopService;
    }

    @Autowired
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    //Could be improved with binary search, dependency is to add attribute that holds distance from arbitrary point to station class and then compare the distance from action to that same point.
    //Also, stationList should be fetched from database just once during app boot to reduce ovehread on database
    @Transactional
    public void mapActionToStation(Action action){
        List <Station> stationList = stationService.getStations();
        double distance = 0.0;
        double minDistance = Double.MAX_VALUE;
        int loopCounter = 0;
        int stationIndex = 0;

        for (Station station : stationList){
            distance = Math.sqrt(
                    ((station.getLatitude() - action.getLatitude())
                            * (station.getLatitude() - action.getLatitude()))
                    + ((station.getLongitude() - action.getLongitude())
                            * (station.getLongitude() - action.getLongitude()))
                    );
            if (minDistance > distance) {
                minDistance = distance;
                stationIndex = loopCounter;
            }
            loopCounter++;
        }
        List <Stop> stopList = stopService.getStopsOnStationBetween(action.getBus().getId(),
                stationList.get(stationIndex).getId(),
                action.getTimestamp().minusMinutes(1),
                action.getTimestamp().plusMinutes(4));
        if (!stopList.isEmpty()){
            return;
        }

        Stop stop = new Stop(action.getTimestamp());
        stop.addStop(stationList.get(stationIndex), action.getBus());
        stopService.addNewStop(stop);
    }

    public float calculateTimeBetweenStationsByIdBetween(Long busId, Long beginStationId,
                                                         Long endStationId,
                                                         LocalDateTime beginDate,
                                                         LocalDateTime endDate){
        if (endStationId == beginStationId){
            throw new IllegalStateException("Beggining station cannot be the same as ending station");
        }

        List <Stop> stopList = stopService.getAllBusStopsByIdBetween(busId, beginDate, endDate);

        if (stopList.size() > 1){
            int startingIndex = 0;
            long minutes;
            int minutesTotal = 0;
            int zbroj = 0;
            int passes = 0;
            int size = stopList.size();

            startingIndex = this.findNextBeginningIndex(startingIndex, beginStationId, stopList);
            if(startingIndex == -1) {
                throw new IllegalStateException("There is no beginStation with id: " + beginStationId + " in fetched data");
            }

            for (int i = startingIndex;i < (size -1); i++){
                minutes = ChronoUnit.MINUTES.between(stopList.get(i).getTimestamp(), stopList.get(i + 1).getTimestamp());
                zbroj = zbroj + (int)minutes;

                if (endStationId == stopList.get(i + 1).getStation().getId()){
                    passes++;
                    minutesTotal = minutesTotal + zbroj;
                    zbroj = 0;
                    if (i == (size - 1)){
                        i = this.findNextBeginningIndex(i + 1, beginStationId, stopList);
                    }
                    else {
                        i = this.findNextBeginningIndex(i + 2, beginStationId, stopList);
                    }
                    if (i == -1){
                        break;
                    }
                        i = i - 1;
                }
                if (minutes > 12){
                    zbroj = 0;
                    i = this.findNextBeginningIndex(i, beginStationId, stopList);
                    if (i == -1){
                        break;
                    }
                    i = i - 1;
                }
            }
            return (float) minutesTotal / passes;
        }
        else {
            throw new IllegalStateException("There is not enough data to calculate average time between stations. Try increasing time window");
        }
    }

    public int findNextBeginningIndex(int currentIndex, Long beginStationId, List<Stop> stopList){
        for (int i = currentIndex; i < stopList.size(); i++){
            if (beginStationId == stopList.get(i).getStation().getId()){
                return i;
            }
        }
        return -1;
    }

    public float getTimeBetweenStationsById(GpsTimeBetweenStationsDTO dto){
        final float result = this.calculateTimeBetweenStationsByIdBetween(dto.getBusId(),
                dto.getBeginStationId(),
                dto.getEndStationId(),
                dto.getBeginDate(),
                dto.getEndDate());
        return result;
    }

    public List <TimespanPassengerReportDTO> TimespanPassengerReport(DateDTO dateDTO){
        List <TimespanPassengerReportDTO> dtoLIst = new ArrayList<>();
        List<Object[]> objectList= actionService.getTimespanPassengerReport(dateDTO.getBeginDate(), dateDTO.getEndDate());
        for (Object[] object : objectList){
            TimespanPassengerReportDTO tempDto = new TimespanPassengerReportDTO();
            tempDto.setId((long)object[0]);
            tempDto.setCount((long)object[1]);
            dtoLIst.add(tempDto);
        }
        return dtoLIst;
    }
/*
    public List<Station> getAllDistinctStops(){
        return stopService.
    }
*/
}
