package com.example.TACS2021UTN.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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


    /**
     * @return validates if the card is correct to be added to the deck
     */
    //TODO make entity attribute and refactor
    public Boolean correctCard(){

        return this.name != null && this.strength != null && this.intelligence != null
                && this.speed != null && this.durability != null && this.power != null
                && this.combat != null;
    }





}
