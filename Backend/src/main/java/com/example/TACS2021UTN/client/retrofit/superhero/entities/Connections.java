package com.example.TACS2021UTN.client.retrofit.superhero.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connections {
    @SerializedName("group-affiliation")
    public String groupaffiliation;
    public String relatives;
}
