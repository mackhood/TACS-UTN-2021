package com.example.TACS2021UTN.models;


import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.state.Created;
import com.example.TACS2021UTN.models.state.State;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game extends PersistantEntity {

    private PlayerGame creator;
    private PlayerGame challenged;
    private Deck deck;
    private LocalDate dateOfCreation;
    private List<Duel> duels = new ArrayList<>();
    private State state;
    private Attribute lastAtrribute = null;


    public Game(User creator, User challenged, Deck deck) {
        this.creator =  new PlayerGame(creator, this);
        this.challenged =  new PlayerGame(challenged, this);
        this.deck = deck;
        this.dateOfCreation = LocalDate.now();
        this.state = new Created();
    }


    public boolean startGame()
    {
        return this.state.startGame(this);
    }
    /*
    public void play(){
        this.state.play(this);
    }
*/
    public Long getIdFromCreator(){
        return getCreator().getPlayer().getId();
    }
    public String getUsernameFromCreator(){
        return getCreator().getPlayer().getUsername();
    }

    public Long getIdFromChallenged(){ return getChallenged().getPlayer().getId(); }
    public String getUsernameFromChallenged(){return getChallenged().getPlayer().getUsername(); }

    public Boolean isReadyToPlayDuel() {
        return attributeHasBeenSelected() && cardsHasBeenChosen();
    }

    private Boolean cardsHasBeenChosen() {
        return (this.getCreator().getLastCardSelected() != null) && (this.getChallenged().getLastCardSelected() != null);
    }

    private Boolean attributeHasBeenSelected() {
        return this.getLastAtrribute() != null;
    }

    public void addDuel(Attribute attribute) {
        Duel newDuel = new Duel();
        newDuel.setAttribute(attribute);
        newDuel.setCreatorCard(this.getCreator().getNextCard());
        newDuel.setChallengedCard(this.getCreator().getNextCard());
        newDuel.setWinner(this.getWinner(newDuel));

    }

    private PlayerGame getWinner(Duel newDuel) {
        return (newDuel.getCreatorCard().getValueOfAttribute(newDuel.getAttribute()) - newDuel.getChallengedCard().getValueOfAttribute(newDuel.getAttribute())) >0 ? this.getCreator() : this.getChallenged();
    }

    public boolean validateGameHasFinished() {
        return this.getCreator().getMainCards().size() == 0 || this.getChallenged().getMainCards().size() == 0;
    }

    public PlayerGame getFinalWinner() {
        return ((this.getCreator().getGainedCards().size() - this.getChallenged().getGainedCards().size()) > 0 ? this.getCreator() : this.getChallenged());
    }

    public void play(User user, Attribute attribute)
    {
        this.addDuel(attribute);
    }
}
