package com.example.TACS2021UTN.entities.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {

    private Long id;
    private String name;
    private String accessToken;

}
