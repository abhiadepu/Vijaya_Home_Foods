package com.vijaya.cart_service.controllers;


import com.netflix.discovery.converters.Auto;
import com.vijaya.cart_service.Models.Cart;
import com.vijaya.cart_service.payloads.CartDTO;
import com.vijaya.cart_service.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(
            @RequestParam Long userId,
            @RequestParam Long itemId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(cartService.addToCart(userId, itemId, quantity));
    }

    @GetMapping("/get")
    public ResponseEntity<CartDTO> getCart(HttpServletRequest request){
        return cartService.getCart(request);
    }

    @GetMapping("getuid")
    public Long getUserId(HttpServletRequest request){
        Long userId = Long.valueOf(request.getHeader("X-User-Id"));
        return userId;
    }

}
