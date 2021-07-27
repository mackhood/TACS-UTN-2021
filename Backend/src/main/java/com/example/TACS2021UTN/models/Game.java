package com.example.TACS2021UTN.models;


import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.state.Created;
import com.example.TACS2021UTN.models.state.State;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game extends PersistantEntity {

    @OneToOne(cascade=CascadeType.ALL)
    private PlayerGame creator;
    @OneToOne(cascade=CascadeType.ALL)
    private PlayerGame challenged;
    @OneToOne(cascade=CascadeType.ALL)
    private Deck deck;
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Duel> duels = new ArrayList<>();
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    public Game(User creator, User challenged, Deck deck) {
        this.creator =  new PlayerGame(creator, this);
        this.challenged =  new PlayerGame(challenged, this);
        this.deck = deck;
        this.dateOfCreation = LocalDate.now();
        this.state = new Created(this);
    }


    public boolean startGame()
    {
        return this.state.startGame(this);
    }

    public Duel play(User user, Attribute attribute) throws NonPlayebleGameStateException, UserWithoutTurnException {
        return this.state.play(user, attribute);
    }

    public Long getIdFromCreator(){
        return getCreator().getPlayer().getId();
    }
    public String getUsernameFromCreator(){
        return getCreator().getPlayer().getUsername();
    }

    public Long getIdFromChallenged(){ return getChallenged().getPlayer().getId(); }
    public String getUsernameFromChallenged(){return getChallenged().getPlayer().getUsername(); }

    public Duel addDuel(Attribute attribute) {
        Duel newDuel = new Duel();
        newDuel.setGame(this);
        newDuel.setAttribute(attribute);
        newDuel.setCreatorCard(this.getCreator().getNextCard());
        newDuel.setChallengedCard(this.getChallenged().getNextCard());
        newDuel.getWinner();
        this.duels.add(newDuel);
        return newDuel;
    }

    public boolean validateGameHasFinished() {
        return !this.getCreator().areTurnsLeft() || !this.getChallenged().areTurnsLeft();
    }

    public PlayerGame getFinalWinner() {
        return ((this.getCreator().getGainedCards().size() - this.getChallenged().getGainedCards().size()) > 0 ? this.getCreator() : this.getChallenged());
    }

    public void addCardsToUser(User user, Card ... cards){
        PlayerGame hand = getPlayerGameByUser(user);
        hand.addGainedCards(cards);
    }

    public PlayerGame getPlayerGameByUser(User user){
        if(creator.getPlayer().equals(user))
            return creator;
        else if (challenged.getPlayer().equals(user))
            return challenged;

        return null;
    }

    public void changeTurn(){
        creator.playTurn();
        challenged.playTurn();
    }

    public Integer cardsLeft()
    {
        return creator.cardsLeft();
    }

    public Boolean userIsInGame(User user){
        return creator.getPlayer().equals(user) || challenged.getPlayer().equals(user);
    }

    public Boolean isUserTurn(User user){
        return getPlayerGameByUser(user).getIsMyTurn();
    }

    public Card getNextCardForPlayerTurn(User user){
        return getPlayerGameByUser(user).getNextCard();

    }
}
