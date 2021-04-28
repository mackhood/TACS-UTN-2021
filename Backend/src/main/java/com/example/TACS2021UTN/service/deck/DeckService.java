package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeckService implements IDeckService {

    private IDeckRepository deckRepository;
    private ModelMapper  modelMapper;

    public DeckService(IDeckRepository deckRepo, ModelMapper modelMapper){
        this.deckRepository = deckRepo;
        this.modelMapper = modelMapper;;
    }

    public Deck getDeckBy(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }

    public Deck getDeckByName(String deck) throws DeckNotFoundException {
        return deckRepository.findByName(deck)
                .orElseThrow(() -> new DeckNotFoundException(deck));
    }

    public void deleteDeck(@Valid DeckDTO deck) {

        //TODO: realizar mappeo de DeckDTO a Deck

        deckRepository.delete(new Deck());
    }

    public void deleteDeckbyId(Long id) {
        deckRepository.deleteById(id);
    }

    @Override
    public void updateDeck(Long deckId, Deck deckDetails) throws DeckNotFoundException {

        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new NotFoundException(
                        "Deck not found with id: " + deckId.toString())
                );

        deck.setCardList(deckDetails.getCardList());
        deck.setName(deckDetails.getName());
        deckRepository.save(deck);

    }

    @Override
    public List<DeckDTO> getAllDecks() {
        List<Deck> decks = deckRepository.findAll();
        return decks.stream().map(d -> deckToDTO(d)).collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> getDeckCards(Long id) {
        Optional<Deck> deck = deckRepository.findById(id);

        if(!deck.isPresent()){
            //TODO: arriba tirar orElseThrow...
        }

        List<Card> deckCards = deck.get().getCardList();

        return deckCards.stream().map(card -> cardToDTO(card)).collect(Collectors.toList());

    }

    @Override
    public DeckDTO findById(Long id)
    {
        Deck deck = deckRepository.findById(id).orElseThrow(() ->
             new NotFoundException("Deck not found with id: " + id)
        );
        DeckDTO cardDTO = modelMapper.map(deck, DeckDTO.class);
        return cardDTO;
    }

    @Override
    public DeckDTO createDeck(DeckDTO deckRequest)
    {
        Deck deck = modelMapper.map(deckRequest, Deck.class);
        //validate cards with all required atributtes

        deckRepository.save(deck);

        return deckRequest;
    }

    DeckDTO deckToDTO(Deck deck){
        return new DeckDTO(deck.getName(), deck.getCardList());
    }

    //TODO: falta implementar
    CardDTO cardToDTO(Card card){
        return new CardDTO();
    }

}
