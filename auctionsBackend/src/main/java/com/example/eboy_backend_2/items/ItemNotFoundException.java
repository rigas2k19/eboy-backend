package com.example.eboy_backend_2.items;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Integer id) { super("Could not find item with id " + id);}
}
