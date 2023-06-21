package com.example.eboy_backend_2.xml;

import com.example.eboy_backend_2.bids.Bid;
import com.example.eboy_backend_2.items.Item;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
public class ExportXml {
    private Float currently;

    private Float buy_price;

    private Float first_bid;

    private Integer number_of_bids;

    private LocalDateTime started;

    private LocalDateTime ends;

    private Boolean auctionStarted;

    private Boolean auctionEnds;

    private String seller;

    List<Item> items;

    List<Bid> bids;

    public ExportXml() {
    }

    public ExportXml(Float currently, Float buy_price, Float first_bid, Integer number_of_bids, LocalDateTime started, LocalDateTime ends, Boolean auctionStarted, Boolean auctionEnds, String seller, List<Item> items, List<Bid> bids) {
        this.currently = currently;
        this.buy_price = buy_price;
        this.first_bid = first_bid;
        this.number_of_bids = number_of_bids;
        this.started = started;
        this.ends = ends;
        this.auctionStarted = auctionStarted;
        this.auctionEnds = auctionEnds;
        this.seller = seller;
        this.items = items;
        this.bids = bids;
    }

    public Float getCurrently() {
        return currently;
    }

    public void setCurrently(Float currently) {
        this.currently = currently;
    }

    public Float getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(Float buy_price) {
        this.buy_price = buy_price;
    }

    public Float getFirst_bid() {
        return first_bid;
    }

    public void setFirst_bid(Float first_bid) {
        this.first_bid = first_bid;
    }

    public Integer getNumber_of_bids() {
        return number_of_bids;
    }

    public void setNumber_of_bids(Integer number_of_bids) {
        this.number_of_bids = number_of_bids;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getEnds() {
        return ends;
    }

    public void setEnds(LocalDateTime ends) {
        this.ends = ends;
    }

    public Boolean getAuctionStarted() {
        return auctionStarted;
    }

    public void setAuctionStarted(Boolean auctionStarted) {
        this.auctionStarted = auctionStarted;
    }

    public Boolean getAuctionEnds() {
        return auctionEnds;
    }

    public void setAuctionEnds(Boolean auctionEnds) {
        this.auctionEnds = auctionEnds;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}

