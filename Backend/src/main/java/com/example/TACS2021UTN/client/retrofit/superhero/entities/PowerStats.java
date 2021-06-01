package com.example.TACS2021UTN.client.retrofit.superhero.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerStats {
    public String intelligence;
    public String strength;
    public String speed;
    public String durability;
    public String power;
    public String combat;
}
