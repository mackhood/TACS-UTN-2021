package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.PersistantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends PersistantEntity {

    private String username;
    private String password;
    private String email;
    private List<Role> roles;
}
