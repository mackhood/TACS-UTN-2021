package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.User;

public abstract class State {
    public abstract boolean startGame(Game game);
    public abstract void flipCoin(Game game);
    public abstract void play(Game game);
    public abstract User winner();
}
