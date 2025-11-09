package com.vijaya.cart_service.Repositories;

import com.vijaya.cart_service.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserId(Long userId);
}
