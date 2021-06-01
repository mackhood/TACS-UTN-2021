package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Created extends State{

    public Created(Game game){
        super();
        this.game = game;
    }

    @Override
    public boolean startGame(Game game)
    {
        game.getDeck().shuffle();
        flipCoin(game);
        dealCards(game);
        game.setState(new InProgress(this.game));
        return true;
    }

    @Override
    public void flipCoin(Game game)
    {
        int number = new Random().nextInt(2);
        PlayerGame playerWithTurn = number == 0 ? game.getCreator() : game.getChallenged();
        playerWithTurn.setPlayerWithTurn();
    }

    @Override
    public Duel play(User user, Attribute attribute) throws NonPlayebleGameStateException {
        throw new NonPlayebleGameStateException(toString());
    }

    @Override
    public User winner() {
        return null;
    }

    private void dealCards(Game game){
        List<List<Card>> partitions = game.getDeck().splitInNParts(2);

        List<PlayerGame> playerGames = new ArrayList<>();
        game.getCreator().setMainCards(partitions.get(0));
        game.getChallenged().setMainCards(partitions.get(1));

    }

    @Override
    public String getName() {
        return this.toString();
    }

    @Override
    public Integer getStateCode() {
        return State.CREATED;
    }

    @Override
    public String toString(){
        return "CREATED";
    }
}
