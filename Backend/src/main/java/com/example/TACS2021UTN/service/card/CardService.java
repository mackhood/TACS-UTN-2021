package com.example.TACS2021UTN.service.card;


import com.example.TACS2021UTN.DTO.AppearenceStatDTO;
import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.ImageDTO;
import com.example.TACS2021UTN.DTO.PowerStatDTO;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<CardDTO> cardDTOS = new ArrayList<>();
        list.forEach(card -> {

            cardDTOS.add(cardToDTO(card));
        });



        return cardDTOS;
    }

    @Override
    public CardDTO findById(long id)
    {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isPresent())
            throw new NotFoundException("Card not found with id: " + id);
        CardDTO cardDTO = cardToDTO(card.get());
        return cardDTO;
    }

    public static CardDTO cardToDTO(Card card){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId().toString());
        cardDTO.setName(card.getName());
        AppearenceStatDTO appearenceStatDTO = new AppearenceStatDTO();//TODO ADD ALL THE ATTRIBUTES to the initial DTO
        PowerStatDTO powerStatDTO = new PowerStatDTO();
        powerStatDTO.setPower(card.getPower().toString());
        powerStatDTO.setCombat(card.getCombat().toString());
        powerStatDTO.setIntelligence(card.getIntelligence().toString());
        powerStatDTO.setStrength(card.getStrength().toString());
        powerStatDTO.setSpeed(card.getSpeed().toString());

        cardDTO.setAppearance(appearenceStatDTO);
        cardDTO.setPowerstats(powerStatDTO);
        ImageDTO imageDTO = new ImageDTO();//TODO

        return cardDTO;
    }

}
