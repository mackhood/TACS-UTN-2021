package com.example.TACS2021UTN.entities.state;

import com.example.TACS2021UTN.entities.Game;

public class InProgress extends State {


    @Override
    public boolean startGame(Game game) {
        return false;
    }

    @Override
    public void flipCoin(Game game) {
        return;
    }

    @Override
    public void play(Game game) {

    }
}
