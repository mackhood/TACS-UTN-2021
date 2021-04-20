package com.example.TACS2021UTN.entities.user;

import com.example.TACS2021UTN.entities.Card;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Player;
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
    private Player player;
    @JsonIgnore
    private Game game;
    @JsonIdentityReference(alwaysAsId = true)
    private List<Card> mainCards = new ArrayList<>();
    @JsonIdentityReference(alwaysAsId = true)
    private List<Card> gainedCards = new ArrayList<>();

    private Boolean isMyTurn = false;

    public PlayerGame(Player player, Game game)
    {
        this.player = player;
        this.game = game;
    }

    public Card getNextCard(){
        return this.getMainCards().stream().findFirst().orElse(null);
    }

    public void setPlayerWithTurn() {
        this.isMyTurn = true;
    }

}
