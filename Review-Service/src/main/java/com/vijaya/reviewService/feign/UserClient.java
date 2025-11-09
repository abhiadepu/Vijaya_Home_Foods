package com.vijaya.reviewService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/auth/{id}")
    Object getUser(@PathVariable("id") Long id);

    @GetMapping("/auth/extract-userid")
    Long extractUserIdFromToken(@RequestHeader("Authorization") String token);
}
