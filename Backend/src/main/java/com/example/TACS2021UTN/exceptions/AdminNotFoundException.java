package com.example.TACS2021UTN.exceptions;

public class AdminNotFoundException extends Exception {


    public AdminNotFoundException(String param) {

        super(String.format("Admin is not found with  : '%s'", param));
    }


}
