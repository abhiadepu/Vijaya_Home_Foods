package com.vijaya.cart_service.controllers;


import com.netflix.discovery.converters.Auto;
import com.vijaya.cart_service.Models.Cart;
import com.vijaya.cart_service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestParam Long userId,
            @RequestParam Long itemId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(cartService.addToCart(userId, itemId, quantity));
    }

}
