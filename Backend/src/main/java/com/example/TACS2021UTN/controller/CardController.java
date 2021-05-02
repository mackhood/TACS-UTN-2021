package com.example.TACS2021UTN.controller;


import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.user.Admin;
import com.example.TACS2021UTN.service.card.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
public class CardController {


    private final ICardService service;

    public CardController(ICardService cardService){
        this.service = cardService;
    }

    @GetMapping("/cards")
    public ResponseEntity<JSONWrapper> getAllCards(){
        return ResponseEntity.ok(new JSONWrapper<>((List<CardDTO>) service.getAllCards()));
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
