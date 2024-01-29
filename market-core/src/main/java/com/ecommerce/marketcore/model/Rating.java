package com.ecommerce.marketcore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName="id")
    private Item item;
}
