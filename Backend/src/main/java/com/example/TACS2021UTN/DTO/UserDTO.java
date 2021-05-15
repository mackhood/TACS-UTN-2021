package com.example.TACS2021UTN.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String id;
    private String password;
    private String email;
    private String username;
    private List<RoleDTO> roles;

}
