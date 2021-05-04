package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Created extends State{
    @Override
    public boolean startGame(Game game)
    {
        game.getDeck().shuffle();
        flipCoin(game);
        dealCards(game);
        //game.setState(new InProgress());
        return true;
    }

    @Override
    public void flipCoin(Game game)
    {
        int number = new Random(2).nextInt();
        PlayerGame playerWithTurn = number == 1 ? game.getCreator() : game.getChallenged();
        playerWithTurn.setPlayerWithTurn();
    }

    @Override
    public void play(Game game) {
        return;
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
}
