package com.example.eboy_backend_2.auctions;

public class AuctionNotFoundException extends RuntimeException{
    public AuctionNotFoundException(Integer id) { super("Could not find auction with id " + id);}
}