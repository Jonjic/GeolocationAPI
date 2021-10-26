package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping (path = "/{userID}")
    public User getUser(@PathVariable ("userID") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public void addNewUser(@RequestBody User newUser){
        userService.saveUser(newUser);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }

    @PutMapping(path = "{userId}")
    public void alterUser(
            @PathVariable ("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    )
    {
        userService.updateUser(userId, name, email);
    }

}
