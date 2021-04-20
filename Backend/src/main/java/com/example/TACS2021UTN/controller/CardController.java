package com.example.TACS2021UTN.controller;


import com.example.TACS2021UTN.exceptions.CardNotFoundException;
import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.entities.Card;
import com.example.TACS2021UTN.service.card.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
public class CardController {

    @Autowired
    private ICardService cardService;

    @GetMapping("/cards")
    public List<Card> getAllCards(){
        List<Card> cards = new ArrayList<>();

        Card card1 = new Card();
        card1.setName("Atom IV");
        card1.setIntelligence(100);
        card1.setSpeed(27);
        card1.setDurability(35);
        card1.setPower(3);
        card1.setCombat(70);
        card1.setId(new Long(55));

        Card card2 = new Card();
        card2.setName("Anti-Spawn");
        card2.setIntelligence(7);
        card2.setSpeed(33);
        card2.setDurability(0);
        card2.setPower(37);
        card2.setCombat(50);
        card2.setId(new Long(50));

        Card card3 = new Card();
        card3.setName("A-Bomb");
        card3.setIntelligence(38);
        card3.setSpeed(17);
        card3.setDurability(80);
        card3.setPower(24);
        card3.setCombat(64);
        card3.setId(new Long(1));

        Card card4 = new Card();
        card4.setName("Abe Sapien");
        card4.setIntelligence(88);
        card4.setSpeed(35);
        card4.setDurability(65);
        card4.setPower(100);
        card4.setCombat(85);
        card4.setId(new Long(2));

        Card card5 = new Card();
        card5.setName("Abin Sur");
        card5.setIntelligence(50);
        card5.setSpeed(53);
        card5.setDurability(64);
        card5.setPower(99);
        card5.setCombat(65);
        card5.setId(new Long(3));

        Card card6 = new Card();
        card6.setName("Abomination");
        card6.setIntelligence(63);
        card6.setSpeed(53);
        card6.setDurability(90);
        card6.setPower(62);
        card6.setCombat(95);
        card6.setId(new Long(4));

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        return cards;

//         return this.cardService.getAllCards();
    }

    @GetMapping("/cards/{id}")
    public Card getCardById(Long id){
        Card card = new Card();
        card.setName("Atom IV");
        card.setIntelligence(100);
        card.setSpeed(27);
        card.setDurability(35);
        card.setPower(3);
        card.setCombat(70);
        card.setId(id);
        return card;
    }
}
