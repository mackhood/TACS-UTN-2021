package com.example.TACS2021UTN.entities;


import com.example.TACS2021UTN.entities.state.Created;
import com.example.TACS2021UTN.entities.state.State;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.entities.user.PlayerDeck;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class Game {

    private Player creator;
    private Player challenged;
    private Deck deck;
    private Date dateOfCreation;
    private List<Duel> duels = new ArrayList<>();
    private State state;
    private Player playerWithTurn;
    private List<PlayerDeck> playerDecks = new ArrayList<>();

    public Game(Player creator, Player challenged, Deck deck) {
        this.creator = creator;
        this.challenged = challenged;
        this.deck = deck;
        this.dateOfCreation = new Date();
        this.state = new Created();
    }

    public boolean startGame()
    {
        return this.state.startGame(this);
    }

    public void play(){
        this.state.play(this);
    }
}
