package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Game;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlayerGame {
    @JsonIdentityReference(alwaysAsId = true)
    private User player;
    @JsonIgnore
    private Game game;
    @JsonIdentityReference(alwaysAsId = true)
    private List<Card> mainCards = new ArrayList<>();
    @JsonIdentityReference(alwaysAsId = true)
    private List<Card> gainedCards = new ArrayList<>();

    private Card lastCardSelected = null;

    private Boolean isMyTurn = false;

    public PlayerGame(User player, Game game)
    {
        this.player = player;
        this.game = game;
    }

    public Card getNextCard(){
        this.setLastCardSelected(this.getMainCards().stream().findFirst().orElse(null));
        return this.getLastCardSelected();
    }

    public void setPlayerWithTurn() {
        this.isMyTurn = true;
    }


    public void chooseCardFromMainCards(){
        this.mainCards.remove(0);
    }

}
