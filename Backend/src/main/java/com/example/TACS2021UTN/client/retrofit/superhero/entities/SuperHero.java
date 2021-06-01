package com.example.TACS2021UTN.client.retrofit.superhero.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHero {
    public String response;
    public String id;
    public String name;
    public PowerStats powerstats;
    public Biography biography;
    public Appearence appearance;
    public Work work;
    public Connections connections;
    public Image image;
}
