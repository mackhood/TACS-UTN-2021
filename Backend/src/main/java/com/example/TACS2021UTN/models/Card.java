package com.example.TACS2021UTN.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.TACS2021UTN.models.attribute.Attribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Card extends PersistantEntity{

    private String name;
    private Integer strength;
    private Integer intelligence;
    private Integer speed;
    private Integer durability;
    private Integer power;
    private Integer combat;


    public Boolean correctCard() {

        try{
            for (Field f : getClass().getDeclaredFields()) {
                if (f.get(this) == null)
                    return false;
            }
        }
        
        catch (Exception e){
            return false;
        }

        return true;
    }
    public Integer getValueOfAttribute(Attribute attribute){
        //TODO refactor attributes to make more extensible
        return 1;
    }





}
