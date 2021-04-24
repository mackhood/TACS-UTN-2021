package com.example.TACS2021UTN.service.card;


import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService implements ICardService {


    private ICardRepository cardRepository;
    private ModelMapper modelMapper;

    public CardService(ICardRepository cardRepository, ModelMapper mapper){
        this.cardRepository = cardRepository;
        this.modelMapper = mapper;
    }

    @Override
    public List<CardDTO> getAllCards() {

        List<Card> list = cardRepository.findAll();
        List<CardDTO> cardDTOS = list.stream()
                .map(c -> modelMapper.map(c, CardDTO.class))
                .collect(Collectors.toList());

        return cardDTOS;
    }

    @Override
    public CardDTO findById(long id)
    {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isPresent())
            throw new NotFoundException("Card not found with id: " + id);
        CardDTO cardDTO = modelMapper.map(card.get(), CardDTO.class);
        return cardDTO;
    }
}
