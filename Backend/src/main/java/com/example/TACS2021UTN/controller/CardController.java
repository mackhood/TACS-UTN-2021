package com.example.TACS2021UTN.controller;


import com.example.TACS2021UTN.DTO.CardApiDTO;
import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.service.card.ICardService;
import com.example.TACS2021UTN.worker.CardWorker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
public class CardController extends BaseController{


    private final ICardService service;
    private final CardWorker worker; //Temporal para pruebas

    public CardController(ICardService cardService, CardWorker worker){
        this.service = cardService;
        this.worker = worker;
    }

    @GetMapping("/cards")
    public ResponseEntity<JSONWrapper> getAllCards(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "30") Integer size){

        Pageable paging = PageRequest.of(page, getPageSize(size));

        List<CardDTO> cards = service.getAllCards(paging);
        if(cards.isEmpty())
            worker.Execute();

        return ResponseEntity.ok(new JSONWrapper<>((List<CardDTO>) service.getAllCards(paging)));
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
