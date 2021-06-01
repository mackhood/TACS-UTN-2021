package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class State {
    protected Game game;

    public abstract boolean startGame(Game game);
    public abstract void flipCoin(Game game);
    public abstract Duel play(User user, Attribute attribute) throws NonPlayebleGameStateException, UserWithoutTurnException;
    public abstract User winner();
    public abstract String getName();
}
