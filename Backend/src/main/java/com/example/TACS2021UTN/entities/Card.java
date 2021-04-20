package com.example.TACS2021UTN.entities;

import com.example.TACS2021UTN.entities.attribute.Attribute;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

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
