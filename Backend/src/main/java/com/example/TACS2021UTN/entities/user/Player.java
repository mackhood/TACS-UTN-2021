package com.example.TACS2021UTN.entities.user;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;
import com.example.TACS2021UTN.service.deck.IDeckService;
import com.example.TACS2021UTN.service.player.IPlayerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
public class Player extends User{


    @Autowired
    @Transient
    private IDeckService deckService;
    @Transient
    private IPlayerService playerService;

    public Game createGame(String paramDeck, String challenged) throws DeckNotFoundException, PlayerNotFoundException {

        Deck deck = chooseDeck(paramDeck);
       // PlayerGame player = chooseChallenged(challenged);

        return new Game(null,null,deck);
    }

    private Player chooseChallenged(String challenged) throws PlayerNotFoundException {

        return this.playerService.getPlayerByName(challenged);
    }

    private Deck chooseDeck(String paramDeck) throws DeckNotFoundException {

       return this.deckService.getDeckByName(paramDeck);
    }

}
