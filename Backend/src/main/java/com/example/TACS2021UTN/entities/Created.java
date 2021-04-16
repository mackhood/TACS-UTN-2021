package com.example.TACS2021UTN.entities;

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
        game.setState(new InProgress());
        return true;
    }

    @Override
    public void flipCoin(Game game)
    {
        int number = new Random(2).nextInt();
        Player playerWithTurn = number == 1 ? game.getCreator() : game.getChallenged();
        game.setPlayerWithTurn(playerWithTurn);
    }

    @Override
    public void play(Game game) {
        return;
    }

    private void dealCards(Game game){
        List<List<Card>> partitions = game.getDeck().splitInNParts(2);

        List<PlayerDeck> playerDecks = new ArrayList<>();
        playerDecks.add(new PlayerDeck(game.getCreator(), game, partitions.get(0)));
        playerDecks.add(new PlayerDeck(game.getChallenged(), game, partitions.get(1)));

        game.setPlayerDecks(playerDecks);
    }
}
