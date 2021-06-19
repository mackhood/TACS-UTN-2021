package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.PersistantEntity;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "state")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class State extends PersistantEntity {
    public static final Integer CREATED = 0;
    public static final Integer INPROGRESS = 1;
    public static final Integer FINISHED = 2;

    @OneToOne
    protected Game game;

    public abstract boolean startGame(Game game);
    public abstract void flipCoin(Game game);
    public abstract Duel play(User user, Attribute attribute) throws NonPlayebleGameStateException, UserWithoutTurnException;
    public abstract User winner();
    public abstract String getName();
    public abstract Integer getStateCode();

}
