package com.example.DiningReview.repositories;

import com.example.DiningReview.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsRestaurantByName(String name);
    boolean existsRestaurantByZipcode(String zipcode);
    List<Restaurant> findByZipcode(String zipcode);

}
