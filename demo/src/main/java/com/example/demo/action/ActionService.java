package com.example.demo.action;

import com.example.demo.bus.BusRepository;
import com.example.demo.business.GpsDataService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActionService {
    ActionRepository actionRepository;
    GpsDataService gpsDataService;
    UserService userService;

    @Autowired
    public ActionService(ActionRepository actionRepository, BusRepository busRepository, UserService userService) {
        this.actionRepository = actionRepository;
        this.userService = userService;
    }

    @Autowired
    public void setGpsDataService(GpsDataService actionService) {
        this.gpsDataService = actionService;
    }


    public List<Action> getActions(){
        return actionRepository.findActionsEagerly();
    }

    public void saveAction(Action newAction) {
        actionRepository.save(newAction);
    }

    public List <Action> getBusActions(Long busNumber){
        return actionRepository.findBusActions(busNumber);
    }

    public List <Action> getLastBusAction(long userId, Long busNumber){
        User fetchedUser = userService.getUserById(userId);
        if (fetchedUser.getAccessCount() > 0)
        {
            List<Action> actionList = actionRepository.findLastBusAction(busNumber);
            if (actionList.isEmpty()){
                throw new IllegalStateException("No data present for bus number " + busNumber);
            }
            fetchedUser.setAccessCount(fetchedUser.getAccessCount() - 1);
            return actionList;
        }
        else {
            throw new IllegalStateException("User has no access tokens left");
        }
    }

    public List<Object[]> getTimespanPassengerReport(LocalDateTime beginDate, LocalDateTime endDate){
        return actionRepository.countPassengersBetween(beginDate, endDate);
    }

    public void postAction(long userId, Action newAction) {
        actionRepository.save(newAction);
        gpsDataService.mapActionToStation(newAction);
        userService.updateUserAccessCount(userId);
    }
}


