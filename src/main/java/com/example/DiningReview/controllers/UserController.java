package com.example.DiningReview.controllers;

import com.example.DiningReview.models.User;
import com.example.DiningReview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser){
        return this.userRepository.save(newUser);
    }

    @PutMapping("/users/{displayName}")
    public User updateUser(@PathVariable String displayName, @RequestBody User updatedUser){
        Optional<User> optionalUser = this.userRepository.findByDisplayName(displayName);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user was not found in the Database.");
        }
        User user = optionalUser.get();

        user.setCity(updatedUser.getCity());
        user.setState(updatedUser.getState());
        user.setZipcode(updatedUser.getZipcode());
        user.setInterestedInPeanutAllergies(updatedUser.isInterestedInPeanutAllergies());
        user.setInterestedInEggAllergies(updatedUser.isInterestedInEggAllergies());
        user.setInterestedInDairyAllergies(updatedUser.isInterestedInDairyAllergies());

        return this.userRepository.save(user);
    }

    @GetMapping("/users/{displayName}")
    public User getUserByDisplayName(@PathVariable String displayName){
        Optional<User> optionalUser = userRepository.findByDisplayName(displayName);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user was not found in the Database.");
        }
        return optionalUser.get();
    }

    //As part of the backend process that validates a user-submitted dining review,
    // I want to verify that the user exists,
    // based on the user display name associated with the dining review

}
