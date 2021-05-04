package com.example.TACS2021UTN.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {

    private String id;
    private String name;
    private PowerStatDTO powerstats;
    private AppearenceStatDTO appearance;
    private ImageDTO image;



}


