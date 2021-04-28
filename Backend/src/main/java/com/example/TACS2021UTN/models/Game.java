package com.example.TACS2021UTN.models;


import com.example.TACS2021UTN.models.state.Created;
import com.example.TACS2021UTN.models.state.State;
import com.example.TACS2021UTN.models.user.Player;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {

    private PlayerGame creator;

    private PlayerGame challenged;

    private Deck deck;

    private LocalDate dateOfCreation;

    private List<Duel> duels = new ArrayList<>();

    private State state;


    public Game(Player creator, Player challenged, Deck deck) {
        this.creator =  new PlayerGame(creator, this);
        this.challenged =  new PlayerGame(challenged, this);
        this.deck = deck;
        this.dateOfCreation = LocalDate.now();
        this.state = new Created();
    }

    /*
    public boolean startGame()
    {
        return this.state.startGame(this);
    }

    public void play(){
        this.state.play(this);
    }
*/
    public Long getIdFromCreator(){
        return getCreator().getPlayer().getId();
    }

    public Long getIdFromChallenged(){
        return getChallenged().getPlayer().getId();
    }
}
