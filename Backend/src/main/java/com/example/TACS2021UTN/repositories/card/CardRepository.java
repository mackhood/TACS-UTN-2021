package com.example.TACS2021UTN.repositories.card;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepository extends GenericRepository<Card> implements ICardRepository {

    public CardRepository(){
        this.database = load();
    }

    protected List<Card> load(){
        List<Card> cardList = new ArrayList<>();
        Card card1 = new Card("Agent Bob", 10, 10, 10, 10, 10, 10);
        card1.setId(10L);
        cardList.add(card1);
        Card card2 = new Card("A-Bomb", 20, 20, 20, 20, 20, 20);
        card2.setId(1L);
        cardList.add(card2);
        Card card3 = new Card("Abe Sapien", 20, 20, 20, 20, 20, 20);
        card3.setId(2L);
        cardList.add(card3);
        Card card4 = new Card("Abomination", 20, 20, 20, 20, 20, 20);
        card3.setId(4L);
        cardList.add(card4);
        return cardList;
    }
}
