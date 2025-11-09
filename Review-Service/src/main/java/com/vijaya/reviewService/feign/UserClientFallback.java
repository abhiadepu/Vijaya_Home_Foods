package com.vijaya.reviewService.feign;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public Object getUserById(Long id) {
        throw new RuntimeException("User Service not available");
    }
}