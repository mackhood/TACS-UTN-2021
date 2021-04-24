package com.example.TACS2021UTN.controller;


import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.service.card.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
public class CardController {

    @Autowired
    private ICardService cardService;

    @GetMapping("/cards")
    public List<CardDTO> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/cards/{id}")
    public CardDTO getCardById(@PathVariable Long id){
        return cardService.findById(id);
    }
}
