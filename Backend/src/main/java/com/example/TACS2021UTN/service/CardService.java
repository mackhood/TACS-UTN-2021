package com.example.TACS2021UTN.service;


import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.repository.ICardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService {


    private ICardRepository cardRepository;

    public CardService(ICardRepository cardRepository){

        this.cardRepository = cardRepository;
    }

    @Override
    public List<CardDTO> getAllCards() {
        return cardRepository.getAllCards();
    }
}
