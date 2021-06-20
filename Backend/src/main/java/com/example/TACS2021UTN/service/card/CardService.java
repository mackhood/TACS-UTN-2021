package com.example.TACS2021UTN.service.card;


import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService implements ICardService {


    private final ICardRepository cardRepository;
    private final ModelMapper modelMapper;

    public CardService(ICardRepository cardRepository, ModelMapper mapper){
        this.cardRepository = cardRepository;
        this.modelMapper = mapper;
    }

    @Override
    public List<CardDTO> getAllCards(Pageable paging) {
        List<Card> list = cardRepository.findAll(paging).toList();
        List<CardDTO> cardApiDTOS = new ArrayList<>();

        list.forEach(card -> {
            cardApiDTOS.add(modelMapper.map(card, CardDTO.class));
        });

        return cardApiDTOS;
    }

    @Override
    public CardDTO findById(long id)
    {
        Card card = cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card not found with id: " + id));
        return modelMapper.map(card, CardDTO.class);
    }



}
