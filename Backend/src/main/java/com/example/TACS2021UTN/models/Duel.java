package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Duel extends PersistantEntity {

    @ManyToOne
    @JoinColumn(name = "game_id",referencedColumnName = "id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "creator_card_id",referencedColumnName = "id")
    private Card creatorCard;
    @ManyToOne
    @JoinColumn(name = "challenged_card_id",referencedColumnName = "id")
    private Card challengedCard;
    @ManyToOne
    @JoinColumn(name = "attribute_id",referencedColumnName = "id")
    private Attribute attribute;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duel_result_id",referencedColumnName = "id")
    private DuelResult result;

    public User getWinner() {
        if (result == null)
            this.calculateResult();

        return result.getWinner();
    }

    private void calculateResult() {
        DuelResult result = new DuelResult();
        Integer creatorCardValue = this.creatorCard.getValueOfAttribute(this.attribute);
        Integer challengedCardValue = this.challengedCard.getValueOfAttribute(this.attribute);
        this.setResult(result);

        if (creatorCardValue.equals(challengedCardValue)){
            result.setResult(EResult.DRAW);
            game.addCardsToUser(game.getCreator().getPlayer(), creatorCard);
            game.addCardsToUser(game.getChallenged().getPlayer(), challengedCard);
        }
        else{
            if (creatorCardValue > challengedCardValue)
                result.setWinner(game.getCreator().getPlayer());
            else
                result.setWinner(game.getChallenged().getPlayer());

            game.addCardsToUser(result.getWinner(), creatorCard, challengedCard);
            result.setResult(EResult.VICTORY);
        }



    }
}
