package com.example.TACS2021UTN.models.state;

import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("finished")
@AllArgsConstructor
@NoArgsConstructor
public class Finished extends State{

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User winner;

    public Finished(User player, Game game){
        this.winner = player;
        this.game = game;
    }

    @Override
    public boolean startGame(Game game) {
        return false;
    }

    @Override
    public void flipCoin(Game game) {
        return;
    }

    @Override
    public Duel play(User user, Attribute attribute) throws NonPlayebleGameStateException {
        throw new NonPlayebleGameStateException(toString());
    }

    @Override
    public User winner() {
        return this.winner;
    }

    @Override
    public EState getStateEnum() {
        return EState.FINISHED;
    }
}
