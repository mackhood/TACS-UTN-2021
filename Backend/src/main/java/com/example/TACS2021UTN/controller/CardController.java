package com.example.TACS2021UTN.controller;


import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.service.card.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private ICardService cardService;

    @GetMapping("/cards")
    public List<CardDTO> getAllCards(){

        return this.cardService.getAllCards();
    }
}
