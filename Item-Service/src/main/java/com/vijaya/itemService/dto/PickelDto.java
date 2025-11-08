package com.vijaya.itemService.dto;

import java.math.BigDecimal;

public record PickelDto(Long id, String name,String description,String category, String image, BigDecimal price) {
}
