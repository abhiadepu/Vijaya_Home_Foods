package com.vijaya.reviewService.service;

import com.vijaya.reviewService.dto.ReviewDto;
import com.vijaya.reviewService.feign.ItemClient;
import com.vijaya.reviewService.feign.UserClient;
import com.vijaya.reviewService.model.Review;
import com.vijaya.reviewService.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repo;

    @Autowired
    private ItemClient itemClient;

    @Autowired
    private UserClient userClient;

    public ReviewDto createReview(ReviewDto dto, String token) {
        // 1️⃣ Extract userId from JWT using user-service Feign call
        Long userId = userClient.extractUserIdFromToken(token);

        // 2️⃣ Verify user exists
        try {
            userClient.getUser(userId);
        } catch (Exception e) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // 3️⃣ Verify item exists based on category
        try {
            if (dto.category().equalsIgnoreCase("pickel")) {
                itemClient.getPickelById(dto.itemId());
            } else {
                itemClient.getPindiVantaluById(dto.itemId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Item not found with ID: " + dto.itemId());
        }

        // 4️⃣ Save review
        Review review = new Review();
        review.setUserId(userId);
        review.setItemId(dto.itemId());
        review.setCategory(dto.category());
        review.setComment(dto.comment());
        review.setRating(dto.rating());
        review.setCreatedAt(Instant.now());

        Review saved = repo.save(review);

        return new ReviewDto(
                saved.getId(),
                saved.getUserId(),
                saved.getItemId(),
                saved.getCategory(),
                saved.getComment(),
                saved.getRating(),
                saved.getCreatedAt()
        );
    }

    public List<ReviewDto> getByItem(Long itemId) {
        return repo.findByItemId(itemId).stream()
                .map(r -> new ReviewDto(
                        r.getId(),
                        r.getUserId(),
                        r.getItemId(),
                        r.getCategory(),
                        r.getComment(),
                        r.getRating(),
                        r.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getByUser(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(r -> new ReviewDto(
                        r.getId(),
                        r.getUserId(),
                        r.getItemId(),
                        r.getCategory(),
                        r.getComment(),
                        r.getRating(),
                        r.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
