package com.example.DiningReview.repositories;

import com.example.DiningReview.models.DiningReview;
import com.example.DiningReview.models.AdminReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {
    List<DiningReview> findByAdminReviewStatus(AdminReviewStatus status);
    List<DiningReview> findByIdAndAdminReviewStatus(Long id, AdminReviewStatus adminReviewStatus);
}
