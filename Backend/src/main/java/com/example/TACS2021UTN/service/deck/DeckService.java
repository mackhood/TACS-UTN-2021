package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.exceptions.CardNotFoundException;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import com.example.TACS2021UTN.service.card.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckService implements IDeckService {

    private final IDeckRepository deckRepository;
    private final ICardRepository cardRepository;
    private final ModelMapper modelMapper;

    public DeckService(IDeckRepository deckRepo, ICardRepository cardRepository, ModelMapper modelMapper){
        this.deckRepository = deckRepo;
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<DeckDTO> getAllDecks() {
        List<Deck> decks = deckRepository.findAll();
        return decks.stream().map(deck -> modelMapper.map(deck, DeckDTO.class)).collect(Collectors.toList());
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public DeckDTO findById(Long id)
    {
        Deck deck = deckRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Deck not found with id: " + id)
        );
        return modelMapper.map(deck, DeckDTO.class);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void deleteDeckbyId(Long id) {
        if(!deckRepository.deleteById(id))
            throw new NotFoundException("Deck not found with id: " + id);
    }

    public Deck getDeckBy(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public DeckDTO save(DeckRequestDTO deckRequest) throws CardNotFoundException {
        Deck deck = new Deck(deckRequest.getName(), getCorrectCards(deckRequest));
        deckRepository.save(deck);
        DeckDTO deckDTO = new DeckDTO();
        deckDTO.setId(deck.getId());
        return deckDTO; //para no devolver to-do el deck, simplemente el id. Ver si se puede con model mapper (creo que se le declaran los tipos)
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Deck getDeckByName(String deck) throws DeckNotFoundException {
        return deckRepository.findByName(deck)
                .orElseThrow(() -> new DeckNotFoundException(deck));
    }

    @Override
    public void updateDeck(Long deckId, DeckRequestDTO deckRequest){
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new NotFoundException(
                        "Deck not found with id: " + deckId.toString())
                );
        deck.setCardList(getCorrectCards(deckRequest));
        deck.setName(deckRequest.getName());
        deckRepository.update(deck);
    }

    @Override
    public List<CardDTO> getDeckCards(Long id) {
        Deck deck = deckRepository.findById(id).orElseThrow(() -> new NotFoundException("Deck not found with id: " + id));
        List<Card> deckCards = deck.getCardList();
        return deckCards.stream().map(card -> modelMapper.map(card, CardDTO.class)).collect(Collectors.toList());
    }

    //////////////////////////////////////////////////PRIVATE//////////////////////////////////////////////////////////
    private DeckDTO deckToDTO(Deck deck){

        List<CardDTO> deckDTOList = new ArrayList<>();

        for(Card card : deck.getCardList()){
            CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
            deckDTOList.add(cardDTO);
        }

        return new DeckDTO(deck.getName(), deckDTOList);
    }

    private List<Card> getCorrectCards(DeckRequestDTO deckRequest){
        return deckRequest
                .getCardListId()
                .stream()
                .map(id -> cardRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Card not found with id: " + id))
                )
                .filter(Card::correctCard)
                .collect(Collectors.toList());
    }

}
