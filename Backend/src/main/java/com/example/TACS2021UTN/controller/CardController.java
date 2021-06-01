package com.example.TACS2021UTN.controller;


import com.example.TACS2021UTN.DTO.CardApiDTO;
import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.service.card.ICardService;
import com.example.TACS2021UTN.worker.CardWorker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {


    private final ICardService service;
    private final CardWorker worker; //Temporal para pruebas

    public CardController(ICardService cardService, CardWorker worker){
        this.service = cardService;
        this.worker = worker;
    }

    @GetMapping("/cards")
    public ResponseEntity<JSONWrapper> getAllCards(){
        List<CardDTO> cards = service.getAllCards();
        if(cards.isEmpty())
            worker.Execute(); //llenamos el repo para pruebas, esto se va a hacer en otro lado, no lo meto en el load del repo por referencias circulares

        cards = service.getAllCards();

        return ResponseEntity.ok(new JSONWrapper<>((List<CardDTO>) service.getAllCards()));
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
