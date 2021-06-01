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
        return cardList;
    }
}
