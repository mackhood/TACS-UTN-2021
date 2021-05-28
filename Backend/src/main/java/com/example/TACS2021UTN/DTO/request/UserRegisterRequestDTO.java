package com.example.TACS2021UTN.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequestDTO {

    //TODO meter validaciones de regex por ejemplo, long minima, etc
    private String password;
    private String email;
    private String username;

}
