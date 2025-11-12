package com.vijaya.user.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartClient {
    @DeleteMapping("/{userId}/del")
    String deleteCart(@PathVariable Long userId);
}
