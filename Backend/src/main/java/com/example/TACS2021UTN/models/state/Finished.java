package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.User;

public class Finished extends State{

    private User winner;

    public Finished(User player){
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
    public User winner() {
        return this.winner;
    }
}
