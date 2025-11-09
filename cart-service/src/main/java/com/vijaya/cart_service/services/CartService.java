package com.vijaya.cart_service.services;

import com.vijaya.cart_service.Models.Cart;
import com.vijaya.cart_service.payloads.CartDTO;

public interface CartService {

    public CartDTO addToCart(Long userId, Long itemId, Integer quantity);
    public CartDTO getCart(Long userId);
    public String updateQuantity(Long itemId);
}
