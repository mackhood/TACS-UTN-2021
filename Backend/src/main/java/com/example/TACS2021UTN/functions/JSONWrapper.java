package com.example.TACS2021UTN.functions;

import javax.persistence.Entity;
import java.util.List;

public class JSONWrapper<T> {
    List<T> data;

    public JSONWrapper(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
