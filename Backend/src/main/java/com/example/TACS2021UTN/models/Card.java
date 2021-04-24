package com.example.TACS2021UTN.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter @NoArgsConstructor
public class Card extends PersistantEntity{

//     @Transient
//     private List<Attribute> atributes = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer strength;
    private Integer intelligence;
    private Integer speed;
    private Integer durability;
    private Integer power;
    private Integer combat;
}
