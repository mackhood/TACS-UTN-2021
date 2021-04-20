package com.example.TACS2021UTN.entities;

import com.example.TACS2021UTN.entities.user.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
public class Duel extends PersistantEntity{
    @Transient
    public long id;
    @Transient
    public Player ganador;

}
