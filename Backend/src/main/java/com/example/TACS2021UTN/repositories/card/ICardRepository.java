package com.example.TACS2021UTN.repositories.card;

import com.example.TACS2021UTN.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findById(Long id);
}
