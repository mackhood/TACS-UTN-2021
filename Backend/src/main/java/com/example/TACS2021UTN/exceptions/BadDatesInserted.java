package com.example.TACS2021UTN.exceptions;

public class BadDatesInserted extends RuntimeException{
    public BadDatesInserted(String message){
        super(message);
    }
}
