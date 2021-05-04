package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Duel extends PersistantEntity{

    public User ganador;

}
