package com.vijaya.cart_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "item-service")   // service name in Eureka
public interface ItemClient {

    @GetMapping("/api/items/pindivantalu/{itemId}/price")
    BigDecimal getItemPrice(@PathVariable Long itemId);
}

