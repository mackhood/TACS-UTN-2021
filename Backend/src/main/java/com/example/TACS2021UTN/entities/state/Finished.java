package com.example.TACS2021UTN.entities.state;

import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Player;

public class Finished extends State{

    private Player winner;

    public Finished(Player player){
        this.winner = player;
    }

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
        return this.winner;
    }
}
