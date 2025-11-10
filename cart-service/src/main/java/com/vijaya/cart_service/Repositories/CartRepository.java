package com.vijaya.cart_service.Repositories;

import com.vijaya.cart_service.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT DISTINCT c FROM Cart c WHERE c.userId = :userId")
    Optional<Cart> findByUserId(Long userId);

}
