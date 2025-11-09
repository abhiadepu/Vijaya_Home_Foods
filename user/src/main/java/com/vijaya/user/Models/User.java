package com.vijaya.user.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private Long phno;
    private LocalDateTime createdAt;
    private String address;
    private String role = "USER";

    public User() {
    }

    public User(LocalDateTime createdAt) {
        this.createdAt = LocalDateTime.now();
    }

    public User(Long userId, String userName, String email, String password, Long phno, String address) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phno = phno;
        this.createdAt = LocalDateTime.now();
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhno() {
        return phno;
    }

    public void setPhno(Long phno) {
        this.phno = phno;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
