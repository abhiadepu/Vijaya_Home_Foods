package com.vijaya.cart_service.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long userId;


    private double totalAmount;
    private double discount;
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> items;

    public Cart() {
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        userId = userId;
    }



    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Cart(Long cartId, Long userId,  double totalAmount, double discount,  List<CartItem> items) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.createdAt = LocalDateTime.now();
        this.items = items;
    }
}
