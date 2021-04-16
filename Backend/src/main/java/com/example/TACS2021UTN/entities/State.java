package com.example.TACS2021UTN.entities;

public abstract  class State {
    public abstract boolean startGame(Game game);
    public abstract void flipCoin(Game game);
    public abstract void play(Game game);
}
