package com.example.TACS2021UTN.entities.user;

import com.example.TACS2021UTN.entities.Card;
import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Admin extends User{


    @Autowired
    @Transient
    private IDeckService deckService;

    public Deck createDeck(String name, List<Card> cards){
        return deckService.createDeck(new Deck(name,cards));
    }

}
