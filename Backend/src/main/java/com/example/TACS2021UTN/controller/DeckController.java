package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.DTO.StatusCodeDTO;
import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;
import java.util.ArrayList;

@RestController
public class DeckController {

    private final IDeckService service;

    public DeckController(IDeckService deckService){
        this.service = deckService;
    }

    @GetMapping("/decks")
    public List<DeckDTO> getAllDecks()
    {
        return service.getAllDecks();
    } //OK

    @GetMapping("/decks/{id}")
    public DeckDTO getDeckById(@PathVariable Long id)
    {
        return service.findById(id);
    } //OK

    @PostMapping("/decks")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewDeck(@Valid @RequestBody DeckRequestDTO newDeck)
    {
        service.save(newDeck);
    }

    @DeleteMapping("/decks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(@PathVariable Long id){
        service.deleteDeckbyId(id);
    }

    @PutMapping("/decks/{id}")
    public ResponseEntity<?> modifyDeck(@Valid @RequestBody DeckDTO deckModified, @PathVariable Long id) throws DeckNotFoundException {

        //Update

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/decks/{id}/cards")
    public List<CardDTO> getDeckCards(@PathVariable Long id){
        return service.getDeckCards(id);
    }

    @PostMapping("/decks/{id}/cards")
    public ResponseEntity<Deck> addCardToDeck(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/decks/{id}/cards/{cardId}")
    public ResponseEntity<Deck> deleteCardFromDeck(@PathVariable Long id, @PathVariable Long cardId){
        return ResponseEntity.ok().build();
    }

}
