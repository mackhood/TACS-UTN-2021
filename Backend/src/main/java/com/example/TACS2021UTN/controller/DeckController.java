package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DeckController {

    @Autowired
    private IDeckService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/decks")
    public List<DeckDTO> getAllDecks(){
        List<Deck> list = service.getAllDecks();

        List<DeckDTO> mappedDeck = list.stream().map(deck -> modelMapper.map(deck, DeckDTO.class)).collect(Collectors.toList());
        return mappedDeck;
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
