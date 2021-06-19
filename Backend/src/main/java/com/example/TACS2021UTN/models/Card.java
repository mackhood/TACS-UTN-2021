package com.example.TACS2021UTN.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import com.example.TACS2021UTN.exceptions.InvalidAttributeException;
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
@Entity
public class Card extends PersistantEntity{

    @Column(name = "name")
    private String name;
    @Column(name = "strength")
    private Integer strength;
    @Column(name = "intelligence")
    private Integer intelligence;
    @Column(name = "speed")
    private Integer speed;
    @Column(name = "durability")
    private Integer durability;
    @Column(name = "power")
    private Integer power;
    @Column(name = "combat")
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
        try{
            Field field = Card.class.getDeclaredField(attribute.getName());
            return (Integer) field.get(this);
        }
        catch(Exception ex)
        {
            throw new InvalidAttributeException("Invalid attribute " + attribute.getName());
        }
    }
}
