package com.vijaya.reviewService.repository;

import com.vijaya.reviewService.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItemId(Long itemId);
    List<Review> findByUserId(Long userId);
}