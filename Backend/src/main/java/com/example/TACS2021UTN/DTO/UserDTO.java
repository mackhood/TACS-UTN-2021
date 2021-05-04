package com.example.TACS2021UTN.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String id;
    private String name;
    private String password;
    private String email;
    private List<RoleDTO> roles;

}
