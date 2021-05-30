package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.User;

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
        if(game.isReadyToPlayDuel()){

            //game.addDuel();
        }
        if(game.validateGameHasFinished()){
            game.setState(new Finished(game.getFinalWinner().getPlayer()));
        }

    }

    @Override
    public User winner() {
        return null;
    }
}
