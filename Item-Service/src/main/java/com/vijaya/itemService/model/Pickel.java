package com.vijaya.itemService.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pickels")
public class Pickel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String Category;

    private String image;

    private BigDecimal price;


    public Pickel(Long id, String name, String description, String category, String image, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        Category = category;
        this.image = image;
        this.price = price;
    }

    public Pickel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
