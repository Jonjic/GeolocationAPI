package com.example.demo.bus;

import com.example.demo.stop.Stop;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bus")
public class BusController {
    BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public List<Bus> getBuses(){
        return busService.getBuses();
    }

    @GetMapping (path = "/{busId}")
    public Bus getBus(@PathVariable("busId") Long busId){
        return busService.getBusById(busId);
    }

    @GetMapping (path = "/number/{busNumber}")
    public List <Bus> getBusIds(@PathVariable("busNumber") Long busNumber){
        return busService.getBusIds(busNumber);
    }

    @GetMapping(path = "/{busId}/station")
    public List<BusStationDTO> getAllBusStations(@PathVariable("busId") Long busId){
        return busService.getAllBusStations(busId);
    }

    @PostMapping
    public void addNewBus(@RequestBody Bus newBus){
        busService.saveBus(newBus);
    }

    @DeleteMapping (path = "{busId}")
    public void removeBus(@PathVariable(value = "busId") Long busId){
        busService.deleteBus(busId);
    }
}
