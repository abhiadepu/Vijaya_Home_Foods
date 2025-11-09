package com.vijaya.reviewService.dto;

import java.time.Instant;

public record ReviewDto(
        Long id,
        Long userId,
        Long itemId,
        String category,
        String comment,
        int rating,
        Instant createdAt
) {}