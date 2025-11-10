package com.vijaya.cart_service.payloads;

import com.vijaya.cart_service.Models.CartItem;

import java.util.List;

public record CartDTO(
        Long cartId,
        Long userId,
        double totalAmount,
        double discount,
        List<CartItemDTO> items
) {

}


