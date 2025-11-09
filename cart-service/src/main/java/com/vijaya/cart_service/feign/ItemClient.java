package com.vijaya.cart_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ITEM-SERVICE")   // service name in Eureka
public interface ItemClient {

    @GetMapping("/items/{itemId}/price")
    Double getItemPrice(@PathVariable Long itemId);
}

