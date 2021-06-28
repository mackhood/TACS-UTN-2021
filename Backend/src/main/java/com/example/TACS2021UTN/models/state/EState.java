package com.example.TACS2021UTN.models.state;

public enum EState {
    CREATED(0){}, INPROGRESS(1){}, FINISHED(2){};

    private int statusCode;

    public int getStatusCode(){
        return statusCode;
    }

    EState (int statusCode) {
        this.statusCode = statusCode;
    }
}
