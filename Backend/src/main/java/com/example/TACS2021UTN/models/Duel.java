package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.user.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
public class Duel extends PersistantEntity{

    public Player ganador;

}
