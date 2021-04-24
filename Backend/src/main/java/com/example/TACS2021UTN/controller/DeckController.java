package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.DTO.StatusCodeDTO;
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

    @Autowired
    private IDeckService service;

    @GetMapping("/decks")
    public List<DeckDTO> getAllDecks()
    {
        return service.getAllDecks();
    }

    @GetMapping("/decks/{id}")
    public DeckDTO getDeckById(@PathVariable Long id)
    {
        return service.findById(id);
    }

    @PostMapping("/decks")

    public DeckDTO createNewDeck(@Valid @RequestBody DeckDTO newDeck, HttpServletResponse response)
    {
        response.setStatus(HttpStatus.CREATED.value());
        return service.createDeck(newDeck);
    }

    @DeleteMapping("/decks/{id}")
    public ResponseEntity<?> deleteDeck(@PathVariable Long id){
        service.deleteDeckbyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/decks/{id}")
    public Deck modifyDeck(@Valid @RequestBody Deck deckModified, @PathVariable Long id) throws DeckNotFoundException {
//         Deck mappedDeck = modelMapper.map(deckModified, Deck.class);
//         Deck deckToModify = service.getDeckById(id);
//         deckToModify.setCardList(mappedDeck.getCardList());
//         deckToModify.setName(mappedDeck.getName());
//         service.updateDeck(id, deckToModify);
        deckModified.setId(id);
        return deckModified;


//         return ResponseEntity.noContent().build();
    }


    @GetMapping("/decks/{id}/cards")
    public List<Card> getDeckCards(@PathVariable Long id){
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card();
        card1.setName("Atom IV");
        card1.setIntelligence(100);
        card1.setSpeed(27);
        card1.setDurability(35);
        card1.setPower(3);
        card1.setCombat(70);
        card1.setId(new Long(55));

        Card card2 = new Card();
        card2.setName("Anti-Spawn");
        card2.setIntelligence(7);
        card2.setSpeed(33);
        card2.setDurability(0);
        card2.setPower(37);
        card2.setCombat(50);
        card2.setId(new Long(50));

        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    //TODO return 201 created
    @PostMapping("/decks/{id}/cards")
    public ResponseEntity<Deck> addCardToDeck(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/decks/{id}/cards/{cardId}")
    public ResponseEntity<Deck> deleteCardFromDeck(@PathVariable Long id, @PathVariable Long cardId){
        return ResponseEntity.ok().build();
    }

}
