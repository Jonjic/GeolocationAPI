package com.example.demo.bus;

import com.example.demo.stop.Stop;
import com.example.demo.stop.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    BusRepository busRepository;
    StopService stopService;

    @Autowired
    public BusService(BusRepository busRepository, StopService stopService) {
        this.busRepository = busRepository;
        this.stopService = stopService;
    }

    public List<Bus> getBuses(){
        return busRepository.findAll();
    }

    public Bus getBusById(Long busId){
        Optional <Bus> optionalBus = busRepository.findById(busId);
        if (optionalBus.isPresent()) {
            return optionalBus.get();
        }
        else {
            throw new IllegalStateException("Bus with that ID does not exist");
        }
    }

    public void saveBus(Bus newBus){
        Optional <Bus> optionalBus = busRepository.findByRegistration(newBus.getRegistration());
        if (optionalBus.isPresent()){
            throw new IllegalStateException("Bus with the same registration already exists");
        }
        else {
            busRepository.save(newBus);
        }
    }

    public void deleteBus(Long id){
        Optional <Bus> optionalBus = busRepository.findById(id);
        if (optionalBus.isPresent()){
         busRepository.deleteById(id);
        }
    }

    public List<Bus> getBusIds(Long busNumber) {
        return busRepository.findByNumber(busNumber);
    }

    public List<BusStationDTO> getAllBusStations(Long busId) {
        List<Object[]> objects = stopService.getAllBusStations(busId);
        List <BusStationDTO> stationList = new ArrayList<>();
        for (Object[] object: objects){
            BusStationDTO busStationDTO = new BusStationDTO();
            busStationDTO.setCount((Long)object[0]);
            busStationDTO.setAquiredOn((LocalDateTime) object[1]);
            busStationDTO.setBus_id((Long)object[2]);
            busStationDTO.setStation_id((Long)object[3]);
            stationList.add(busStationDTO);
        }
        return stationList;
    }
}
