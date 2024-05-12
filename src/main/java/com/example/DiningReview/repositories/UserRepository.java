package com.example.DiningReview.repositories;

import com.example.DiningReview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDisplayName(String displayName);
}
