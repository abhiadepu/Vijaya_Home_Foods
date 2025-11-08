package com.vijaya.itemService.dto;

import java.math.BigDecimal;


public record PindiVantaluDto(Long id, String name, String description, String category, String image, BigDecimal price) {

}
