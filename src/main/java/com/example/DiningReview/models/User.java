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
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;
    private String city;
    private String state;
    private String zipcode;
    private boolean interestedInPeanutAllergies;
    private boolean interestedInEggAllergies;
    private boolean interestedInDairyAllergies;
}
