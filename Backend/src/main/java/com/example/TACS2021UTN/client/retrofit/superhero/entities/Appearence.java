package com.example.TACS2021UTN.client.retrofit.superhero.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appearence {
    public String gender;
    public String race;
    public List<String> height;
    public List<String> weight;
    @SerializedName("eye-color")
    public String eyecolor;
    @SerializedName("hair-color")
    public String haircolor;
}
