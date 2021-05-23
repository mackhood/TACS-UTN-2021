package com.example.TACS2021UTN.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardApiDTO {

    private String id;
    private String name;
    private PowerStatDTO powerstats;
    private AppearenceStatDTO appearance;
    private ImageDTO image;



}


