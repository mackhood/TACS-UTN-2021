package com.example.TACS2021UTN.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppearenceStatDTO {

    private List<String> height;
    private List<String> weight;


}

