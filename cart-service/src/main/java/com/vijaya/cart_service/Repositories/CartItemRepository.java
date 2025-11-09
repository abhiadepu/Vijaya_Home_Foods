package com.vijaya.cart_service.Repositories;

import com.vijaya.cart_service.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT SUM(c.total) FROM CartItem c WHERE c.cart.cartId = :cartId")
    Double sumLineTotalByCartId(Long cartId);
}
