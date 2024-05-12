package com.example.DiningReview.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DINING_REVIEWS")
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submittedBy; //name of the reviewer
    //private Long restaurantId; //ID of the reviewed restaurant
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    private Integer peanutScore; //scale of 1-5
    private Integer eggScore; //scale of 1-5
    private Integer dairyScore; //scale of 1-5
    private String commentary; //optional commentary
    @Enumerated(EnumType.STRING)
    private AdminReviewStatus adminReviewStatus;
}
