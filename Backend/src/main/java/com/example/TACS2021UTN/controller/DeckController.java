package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.exceptions.CardNotFoundException;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import java.util.List;

@RestController
public class DeckController {

    private final IDeckService service;

    public DeckController(IDeckService deckService){

        this.service = deckService;
    }

    @GetMapping("/decks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JSONWrapper>  getAllDecks()

    {

        return ResponseEntity.ok(new JSONWrapper<>((List<DeckDTO>) service.getAllDecks()));
    } //OK

    @GetMapping("/decks/{id}")
    public ResponseEntity<DeckDTO> getDeckById(@PathVariable Long id)
    {

        return ResponseEntity.ok(service.findById(id));
    } //OK

    @PostMapping("/decks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createNewDeck(@Valid @RequestBody DeckRequestDTO newDeck) throws CardNotFoundException {

        try{
            service.save(newDeck);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/decks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteDeck(@PathVariable Long id){
        service.deleteDeckbyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/decks/{id}")
    public ResponseEntity modifyDeck(@Valid @RequestBody DeckRequestDTO deckModified, @PathVariable Long id) throws DeckNotFoundException, CardNotFoundException {
        service.updateDeck(id,deckModified);
        return ResponseEntity.status(204).build();
    }


    @GetMapping("/decks/{id}/cards")
    public ResponseEntity<JSONWrapper> getDeckCards(@PathVariable Long id){

        return ResponseEntity.ok(new JSONWrapper<>((List<CardDTO>) service.getDeckCards(id)));
    }

    @PostMapping("/decks/{id}/cards")
    public ResponseEntity<Deck> addCardToDeck(@PathVariable Long id){

        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/decks/{id}/cards/{cardId}")
    public ResponseEntity<Deck> deleteCardFromDeck(@PathVariable Long id, @PathVariable Long cardId){
        return ResponseEntity.noContent().build();
    }

}
