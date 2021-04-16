package com.example.TACS2021UTN.entities.user;

import com.example.TACS2021UTN.entities.Card;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlayerDeck {
    private Player player;
    private Game game;
    private List<Card> mainCards = new ArrayList<>();
    private List<Card> gainedCards = new ArrayList<>();

    public PlayerDeck(Player player, Game game, List<Card> cards)
    {
        this.player = player;
        this.game = game;
        this.mainCards = cards;
    }

    public Card getNextCard(){
        return this.getMainCards().stream().findFirst().orElse(null);
    }
}
