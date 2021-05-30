package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Duel extends PersistantEntity {

    private Game game;
    private Card creatorCard;
    private Card challengedCard;
    private Attribute attribute;
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
