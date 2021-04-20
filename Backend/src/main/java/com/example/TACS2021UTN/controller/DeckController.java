package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.Card;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
public class DeckController {

    @Autowired
    private IDeckService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/decks")
    public List<Deck> getAllDecks(){

        List<Card> cards = new ArrayList<>();
        List<Deck> decks = new ArrayList<>();

        Card card1 = new Card();
        card1.setName("Batman");
        card1.setIntelligence(100);
        card1.setSpeed(27);
        card1.setDurability(35);
        card1.setPower(50);
        card1.setCombat(70);
        card1.setId(new Long(1));

        Card card2 = new Card();
        card2.setName("Robin");
        card2.setIntelligence(7);
        card2.setSpeed(33);
        card2.setDurability(25);
        card2.setPower(30);
        card2.setCombat(50);
        card2.setId(new Long(2));

        cards.add(card1);
        cards.add(card2);


        Deck deck1 = new Deck();
        deck1.setName("Super Mazo");
        deck1.setCardList(cards);

        Deck deck2 = new Deck();
        deck1.setName("Mazo Increible");
        deck1.setCardList(cards);
        decks.add(deck1);
        decks.add(deck2);
        return decks;
//         List<Deck> list = service.getAllDecks();
//
//         List<DeckDTO> mappedDeck = list.stream().map(deck -> modelMapper.map(deck, DeckDTO.class)).collect(Collectors.toList());
//         return mappedDeck;
    }

    @GetMapping("/decks/{id}")
    public DeckDTO getDeckById(@PathVariable Long id) throws DeckNotFoundException {
        Deck deck = service.getDeckById(id);
        DeckDTO deckReturned = modelMapper.map(deck, DeckDTO.class);
        return deckReturned;
    }

    @PostMapping("/deck")
    //No retorna nada o un statusCode
    //Solo para usuario admin tiene que ser
    public void createNewDeck(@RequestBody DeckDTO newDeck) {
        Deck mappedDeck = modelMapper.map(newDeck, Deck.class);
        service.createDeck(mappedDeck);
    }

    @DeleteMapping("/decks/{id}")
    //No retorna nada o un statusCode
    //Solo para usuario admin tiene que ser
    public void deleteDeck(@PathVariable Long id){
        service.deleteDeckbyId(id);
    }

    @PutMapping("/decks/{id}")
    public void modifyDeck(@RequestBody DeckDTO deckModified, @PathVariable Long id) throws DeckNotFoundException {
        Deck mappedDeck = modelMapper.map(deckModified, Deck.class);
        Deck deckToModify = service.getDeckById(id);
        deckToModify.setCardList(mappedDeck.getCardList());
        deckToModify.setName(mappedDeck.getName());
        service.updateDeck(id, deckToModify);
    }

}
