package com.example.DiningReview.controllers;

import com.example.DiningReview.models.Restaurant;
import com.example.DiningReview.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id){
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id was not found in the Database.");
        }
        return optionalRestaurant.get();
    }

    @GetMapping("/restaurants/search")
    public List<Restaurant> getRestaurantsByZipCode(@RequestParam String zipcode){
         return this.restaurantRepository.findByZipcode(zipcode);
    }


    public void getRestaurantsByZipCodeAndAllergyScore(){
        // TODO
    }

    @PostMapping("/restaurants")
    public Restaurant createNewRestaurant(@RequestBody Restaurant newRestaurant){
        if(this.restaurantRepository.existsRestaurantByName(newRestaurant.getName()) &&
        this.restaurantRepository.existsRestaurantByZipcode(newRestaurant.getZipcode())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant already exists.");
        }
        return this.restaurantRepository.save(newRestaurant);
    }

}
