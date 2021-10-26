package com.example.demo.action;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/action")
public class ActionController {

    public ActionService actionService;

    @Autowired
    ActionController(ActionService ActionService){
        this.actionService = ActionService;
    }

    @GetMapping
    public List<Action> getActions(){
       return actionService.getActions();
    }

    @PostMapping
    public void addNewAction(@RequestBody Action newAction){
        actionService.saveAction(newAction);
    }

    @PostMapping (path = "/{userId}")
    public void postNewAction(@PathVariable("userId") long userId, @RequestBody Action newAction){
        actionService.postAction(userId, newAction);
    }

    @GetMapping(path = "/bus/{busNumber}")
    public List<Action> getBusActions(@PathVariable ("busNumber") Long busNumber){
       return actionService.getBusActions(busNumber);
    }

    @GetMapping(path = "/last/{busNumber}/{userId}")
    public List<Action> getLastBusAction(@PathVariable ("busNumber") Long busNumber, @PathVariable ("userId") long userId){
        return actionService.getLastBusAction(userId, busNumber);
    }
}
