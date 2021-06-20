package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.PersistantEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayerGame extends PersistantEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User player;
    @OneToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
    @ManyToMany
    @JoinTable(name = "player_game_main_card",
            joinColumns = @JoinColumn(name = "player_game_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> mainCards = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "player_game_gained_card",
            joinColumns = @JoinColumn(name = "player_game_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> gainedCards = new ArrayList<>();
    @Column(name = "turn")
    private Integer turn;
    @Column(name = "is_my_turn")
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
        return Math.max(getNumberMainCards() - turn, 0);
    }

    public Integer getNumberGainedCards(){
        return gainedCards.size();
    }

    public Integer getNumberMainCards(){
        return mainCards.size();
    }
}
