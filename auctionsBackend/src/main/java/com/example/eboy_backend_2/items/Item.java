package com.example.eboy_backend_2.items;

import com.example.eboy_backend_2.auctions.Auction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private String location;
    private String sellerUsername;
    private String description;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="auction_id", nullable = false)
    @JsonIgnore
    private Auction auction;

    public Item(String name, String category, Auction auction, String location, String sellerUsername, String description, String country) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.sellerUsername = sellerUsername;
        this.description = description;
        this.country = country;
        this.auction = auction;
    }

    public Item() {}

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Auction getAuction(){ return auction;}

    public void setAuction(Auction auction){this.auction = auction;}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}