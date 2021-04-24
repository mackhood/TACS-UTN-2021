package com.example.TACS2021UTN.repositories.card;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICardRepository extends JpaRepository<Card, Long> {

}
