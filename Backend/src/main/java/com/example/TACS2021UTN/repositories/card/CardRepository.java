package com.example.TACS2021UTN.repositories.card;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepository implements ICardRepository {

    private final List<Card> database;

    public CardRepository(){
        this.database = load();
    }

    @Override
    public Optional<Card> findById(Long cardId) {
        return database.stream().filter(deck -> deck.getId().equals(cardId)).findFirst();
    }

    @Override
    public List<Card> findAll() {
        return database;
    }

    private List<Card> load(){
        List<Card> cardList = new ArrayList<>();
        Card card1 = new Card("UnaCarta", 10, 10, 10, 10, 10, 10);
        card1.setId(1L);
        cardList.add(card1);
        return cardList;
    }
}
