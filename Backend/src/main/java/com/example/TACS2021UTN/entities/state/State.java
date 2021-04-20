package com.example.TACS2021UTN.entities.state;

import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Player;

public abstract class State {
    public abstract boolean startGame(Game game);
    public abstract void flipCoin(Game game);
    public abstract void play(Game game);
    public abstract Player winner();
}
