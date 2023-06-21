package com.example.eboy_backend_2.bids;

import com.example.eboy_backend_2.auctions.Auction;
import com.example.eboy_backend_2.items.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bidder;

    private LocalDateTime time;

    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="auction_id", nullable = false)
    @JsonIgnore
    private Auction auction;

    public Bid(String bidder, LocalDateTime time, Float amount, Auction auction){
        this.bidder = bidder;
        this.amount = amount;
        this.time = time;
        this.auction = auction;
    }

    public Bid() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }


    public void setAuction(Auction auction){ this.auction = auction; }

    public Auction getAuction(Auction auction){ return this.auction; }
}
