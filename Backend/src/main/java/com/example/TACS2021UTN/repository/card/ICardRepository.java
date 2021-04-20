package com.example.TACS2021UTN.repository.card;

import com.example.TACS2021UTN.DTO.CardDTO;

import java.util.List;

public interface ICardRepository {

    public List<CardDTO> getAllCards();
}
