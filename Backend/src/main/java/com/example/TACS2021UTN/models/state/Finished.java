package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.User;

public class Finished extends State{

    private User winner;

    public Finished(User player, Game game){
        this.winner = player;
        this.game = game;
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
    public Duel play(User user, Attribute attribute) throws NonPlayebleGameStateException {
        throw new NonPlayebleGameStateException(toString());
    }

    @Override
    public User winner() {
        return this.winner;
    }

    @Override
    public String getName() {
        return this.toString();
    }

    @Override
    public String toString(){
        return "FINISHED";
    }
}
