package com.example.TACS2021UTN.entities;

import com.example.TACS2021UTN.entities.attribute.Attribute;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card extends PersistantEntity{

    @Transient
    private List<Attribute> atributes = new ArrayList<>();


    public Card(){



    }



}
