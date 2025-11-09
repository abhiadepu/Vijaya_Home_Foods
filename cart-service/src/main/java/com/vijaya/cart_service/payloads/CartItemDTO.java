package com.vijaya.cart_service.payloads;

public record CartItemDTO(
        Long id,
        Long itemId,
        int quantity,
        double price,
        double total,
        Long cartId
) {
}
