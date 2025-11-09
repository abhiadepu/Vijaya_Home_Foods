package com.vijaya.reviewService.controller;

import com.vijaya.reviewService.dto.ReviewDto;
import com.vijaya.reviewService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ReviewDto createReview(
            @RequestBody ReviewDto dto,
            @RequestHeader("Authorization") String token) {
        return service.createReview(dto, token);
    }

    @GetMapping("/item/{itemId}")
    public List<ReviewDto> getByItem(@PathVariable Long itemId) {
        return service.getByItem(itemId);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewDto> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }
}
