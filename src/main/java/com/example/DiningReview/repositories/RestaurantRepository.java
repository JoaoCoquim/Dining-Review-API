package com.example.DiningReview.repositories;

import com.example.DiningReview.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsRestaurantByName(String name);
    boolean existsRestaurantByZipcode(String zipcode);
    List<Restaurant> findByZipcode(String zipcode);

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "JOIN FETCH r.diningReviews dr " +
            "WHERE r.zipcode = :zipcode " +
            "AND (dr.peanutScore IS NOT NULL OR dr.eggScore IS NOT NULL OR dr.dairyScore IS NOT NULL)" +
            "ORDER BY COALESCE(dr.peanutScore, dr.eggScore, dr.dairyScore) DESC")
    List<Restaurant> findByZipcodeAndAllergyScoreIsNotNullOrderByAllergyScoreDesc(String zipcode);
}
