package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerGame {
    private User player;
    private Game game;
    private List<Card> mainCards = new ArrayList<>();
    private List<Card> gainedCards = new ArrayList<>();
    private Integer turn;
    private Boolean isMyTurn = false;

    public PlayerGame(User player, Game game)
    {
        this.player = player;
        this.game = game;
        this.turn = 0;
    }

    public Card getNextCard(){
        return this.mainCards.get(turn);
    }

    public void setPlayerWithTurn() {
        this.isMyTurn = true;
    }

    public void chooseCardFromMainCards(){
        this.mainCards.remove(0);
    }

    public void addGainedCard(Card card){
        this.gainedCards.add(card);
    }
    public void addGainedCards(Card ... cards){
        Arrays.stream(cards).forEach(c -> this.addGainedCard(c));
    }

    public void playTurn(){
        turn++;
        if(areTurnsLeft())
            setIsMyTurn(!isMyTurn);
        else
            setIsMyTurn(false);

    }

    public Boolean areTurnsLeft(){
        return cardsLeft() > 0;
    }

    public Integer cardsLeft(){
        return Math.max(mainCards.size() - turn, 0);
    }
}
