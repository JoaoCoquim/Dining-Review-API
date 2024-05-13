package com.example.DiningReview.controllers;

import com.example.DiningReview.models.AdminReviewStatus;
import com.example.DiningReview.models.DiningReview;
import com.example.DiningReview.models.Restaurant;
import com.example.DiningReview.repositories.DiningReviewRepository;
import com.example.DiningReview.repositories.RestaurantRepository;
import com.example.DiningReview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class DiningReviewController {

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dining-reviews")
    public List<DiningReview> getAllDiningReviews(){
        return this.diningReviewRepository.findAll();
    }

    @PostMapping("/dining-reviews")
    public DiningReview createNewDiningReview(@RequestBody DiningReview diningReview){
        if (diningReview.getRestaurant() == null || diningReview.getSubmittedBy() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body. RestaurantId and SubmittedBy are required.");
        }
        // Check if the restaurant exists
        Long restaurantId = diningReview.getRestaurant().getId();
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with ID " + restaurantId + " not found.");
        }
        //Check if the user exists
        String submittedBy = diningReview.getSubmittedBy();
        if(this.userRepository.findByDisplayName(diningReview.getSubmittedBy()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with display name " + submittedBy + " not found.");
        }
        diningReview.setAdminReviewStatus(AdminReviewStatus.PENDING);
        return this.diningReviewRepository.save(diningReview);
    }

    @GetMapping("/admin/pending-dining-reviews")
    public List<DiningReview> getPendingReviews(){
        return this.diningReviewRepository.findByAdminReviewStatus(AdminReviewStatus.PENDING);
    }

    @GetMapping("/dining-reviews/approved/{id}")
    public List<DiningReview> getApprovedReviewsByRestaurant(@PathVariable Long id){
        return this.diningReviewRepository.findByIdAndAdminReviewStatus(id, AdminReviewStatus.ACCEPTED);
    }

    @PutMapping("/admin/dining-reviews/{id}/approve")
    public DiningReview approveReview(@PathVariable Long id){
        Optional<DiningReview> optionalReview = this.diningReviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review was not found in the Database.");
        }
        DiningReview reviewToApprove = optionalReview.get();
        reviewToApprove.setAdminReviewStatus(AdminReviewStatus.ACCEPTED);
        return this.diningReviewRepository.save(reviewToApprove);
    }

    @PutMapping("/admin/dining-reviews/{id}/reject")
    public DiningReview rejectReview(@PathVariable Long id){
        Optional<DiningReview> optionalReview = this.diningReviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review was not found in the Database.");
        }
        DiningReview reviewToReject = optionalReview.get();
        reviewToReject.setAdminReviewStatus(AdminReviewStatus.REJECTED);
        return this.diningReviewRepository.save(reviewToReject);
    }
}
