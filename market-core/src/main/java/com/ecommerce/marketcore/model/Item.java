package com.ecommerce.marketcore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String name;

    private String description;

    private double price;

    private int categoryId;

    private int brandId;

    private int stockQuantity;

    private boolean availability;

    private String creationDate;

    private String lastUpdateDate;

    private String imageUrl;

    private double rating;

    private int ratingCount;

    private double discountPercentage;

    private String promotionalText;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private Set<Category> categories = new HashSet<>();
}