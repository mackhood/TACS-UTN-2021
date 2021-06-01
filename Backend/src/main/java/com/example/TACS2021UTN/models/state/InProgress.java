package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.User;

public class InProgress extends State {

    public InProgress(Game game){
        super();
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
    public Duel play(User user, Attribute attribute) throws UserWithoutTurnException{
        if(!game.isUserTurn(user))
            throw new UserWithoutTurnException(user.getUsername());

        Duel newDuel = game.addDuel(attribute);
        game.changeTurn();

        if(game.validateGameHasFinished()){
            game.setState(new Finished(game.getFinalWinner().getPlayer(), this.game));
        }

        return newDuel;
    }

    @Override
    public User winner() {
        return null;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String toString(){
        return "IN PROGRESS";
    }
}
