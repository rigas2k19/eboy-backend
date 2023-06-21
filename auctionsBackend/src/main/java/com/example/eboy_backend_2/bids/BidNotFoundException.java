package com.example.eboy_backend_2.bids;

public class BidNotFoundException extends RuntimeException{

    public BidNotFoundException(Integer id) { super("Could not find bid with id " + id);}
}

