package com.vijaya.reviewService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "item-service")
public interface ItemClient {
    @GetMapping("/api/items/pickels/{id}")
    Object getPickelById(@PathVariable("id") Long id);

    @GetMapping("/api/items/pindivantalu/{id}")
    Object getPindiVantaluById(@PathVariable("id") Long id);
}