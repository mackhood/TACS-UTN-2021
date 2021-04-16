package com.example.TACS2021UTN.entities.user;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.service.DeckService;

public class Player extends User{

    public Game createGame(String paramDeck, String challenged) throws DeckNotFoundException {



        Deck deck = chooseDeck(paramDeck);
       // Player player = chooseChallenged(challenged); TODO
        Player player = null;

        return new Game(this,player,deck);
    }

    private void chooseChallenged(String challenged) {

    }

    private Deck chooseDeck(String paramDeck) throws DeckNotFoundException {

       return new DeckService().getDeckByName(paramDeck);
    }

}
