package com.shann.springredis.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(){
       super("User Not Found");
    }
}
