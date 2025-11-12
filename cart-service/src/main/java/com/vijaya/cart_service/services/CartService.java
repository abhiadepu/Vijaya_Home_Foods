package com.vijaya.cart_service.services;

import com.vijaya.cart_service.Models.Cart;
import com.vijaya.cart_service.payloads.CartDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {

    public CartDTO addToCart(Long userId, Long itemId, Integer quantity);
    public ResponseEntity<CartDTO> getCart(HttpServletRequest request);
    public String updateQuantity(Long itemId);
    public String deleteCart(Long userId);
}
