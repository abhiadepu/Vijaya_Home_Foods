package com.vijaya.cart_service.Repositories;

import com.vijaya.cart_service.Models.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT SUM(c.total) FROM CartItem c WHERE c.cart.cartId = :cartId")
    Double sumLineTotalByCartId(Long cartId);
    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.cart.cartId = :cartId")
    void deleteByCartId(@Param("cartId") Long cartId);
}
