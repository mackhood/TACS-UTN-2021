package com.example.TACS2021UTN.repositories.card;

import com.example.TACS2021UTN.models.Card;

import java.util.List;
import java.util.Optional;

public interface ICardRepository {
    Optional<Card> findById(Long cardId);

    List<Card> findAll();
}
