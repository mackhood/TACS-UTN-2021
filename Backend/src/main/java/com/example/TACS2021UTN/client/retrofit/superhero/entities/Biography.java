package com.example.TACS2021UTN.client.retrofit.superhero.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Biography {
    @SerializedName("full-name")
    public String fullname;
    @SerializedName("alter-egos")
    public String alteregos;
    public List<String> aliases;
    @SerializedName("place-of-birth")
    public String placeofbirth;
    @SerializedName("first-appearance")
    public String firstappearance;
    public String publisher;
    public String alignment;
}
