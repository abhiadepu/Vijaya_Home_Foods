package com.vijaya.reviewService.service;

import com.vijaya.reviewService.dto.ReviewDto;
import com.vijaya.reviewService.exception.ResourceNotFoundException;
import com.vijaya.reviewService.feign.ItemClient;
import com.vijaya.reviewService.feign.UserClient;
import com.vijaya.reviewService.model.Review;
import com.vijaya.reviewService.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @CacheEvict(value = {"reviewsByItem", "reviewsByUser"}, allEntries = true)
    public ReviewDto createReview(ReviewDto dto) {
        try {
            userClient.getUser(dto.userId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with ID: " + dto.userId());
        }

        try {
            if (dto.category().equalsIgnoreCase("pickels") || dto.category().equalsIgnoreCase("pickel")) {
                itemClient.getPickelById(dto.itemId());
            } else if (dto.category().equalsIgnoreCase("pindivantalu")) {
                itemClient.getPindiVantaluById(dto.itemId());
            } else {
                throw new RuntimeException("Invalid category: " + dto.category());
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Item not found with ID: " + dto.itemId());
        }

        Review review = new Review();
        review.setUserId(dto.userId());
        review.setItemId(dto.itemId());
        review.setCategory(dto.category());
        review.setComment(dto.comment());
        review.setRating(dto.rating());
        review.setCreatedAt(Instant.now());

        Review saved = repo.save(review);
        return mapToDto(saved);
    }

    // ✅ Cache results of getByItem
    @Cacheable(value = "reviewsByItem", key = "#itemId")
    public List<ReviewDto> getByItem(Long itemId) {
        System.out.println("⏳ Fetching reviews for itemId " + itemId + " from DB...");
        List<Review> reviews = repo.findByItemId(itemId);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No reviews found for item ID: " + itemId);
        }
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // ✅ Optional: cache reviews per user too
    @Cacheable(value = "reviewsByUser", key = "#userId")
    public List<ReviewDto> getByUser(Long userId) {
        System.out.println("⏳ Fetching reviews for userId " + userId + " from DB...");
        List<Review> reviews = repo.findByUserId(userId);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No reviews found for user ID: " + userId);
        }
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ReviewDto mapToDto(Review r) {
        return new ReviewDto(
                r.getId(),
                r.getUserId(),
                r.getItemId(),
                r.getCategory(),
                r.getComment(),
                r.getRating(),
                r.getCreatedAt()
        );
    }
}
