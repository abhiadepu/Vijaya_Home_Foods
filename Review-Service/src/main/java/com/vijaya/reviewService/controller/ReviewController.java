package com.vijaya.reviewService.controller;

import com.vijaya.reviewService.dto.ReviewDto;
import com.vijaya.reviewService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody ReviewDto dto) {
        ReviewDto saved = service.createReview(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<ReviewDto>> getByItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(service.getByItem(itemId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }
}
