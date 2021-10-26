package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        Optional <User> optionalUser =
                userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            throw new IllegalStateException("User with that email already exists");
        }
        //check if email is valid and so on
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new IllegalStateException("User with id: " +id + "does not exist");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long studentID, String name, String email) {
        User user;
        Optional<User> optionalUser = userRepository.findById(studentID);
        if (!optionalUser.isPresent()){
            throw new IllegalStateException("User with id: " + studentID + "does not exist");
        }
        else{
            user = optionalUser.get();
        }

        //Logika za ime i mail
        if (name != null && !Objects.equals(user.getFirstName(), name)){
            user.setFirstName(name);
        }

        if (email != null && email.length() > 2 &&
                !Objects.equals(user.getEmail(), email)){
            //Vidit jel ima neki vec user sa tim mailom
            Optional <User> userWithSameEmail = userRepository.findUserByEmail(email);
            if (userWithSameEmail.isPresent()){
                System.out.println(userWithSameEmail.get().getEmail());
                throw new IllegalStateException("User with that mail already exists");
            }
            user.setEmail(email);
        }
    }

    @Transactional
    public void updateUserAccessCount(Long studentID) {
        User user;
        Optional<User> optionalUser = userRepository.findById(studentID);
        if (!optionalUser.isPresent()){
            throw new IllegalStateException("User with id: " + studentID + "does not exist");
        }
        else {
            user = optionalUser.get();
        }
        user.setAccessCount(user.getAccessCount() + 5);
    }

    public User getUserById(Long userId) {
        Optional <User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new IllegalStateException("User with that ID does not exist");
        }
    }
}
