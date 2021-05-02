package com.example.TACS2021UTN.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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
}
