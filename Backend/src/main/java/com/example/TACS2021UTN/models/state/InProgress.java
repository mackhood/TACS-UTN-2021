package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Player;

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

    @Override
    public Player winner() {
        return null;
    }
}
