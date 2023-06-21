package com.example.eboy_backend_2.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String id) { super("Could not find user with username " + id);}
}
