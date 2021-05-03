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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        List<DeckDTO> deckDTOList = new ArrayList<DeckDTO>();

        return decks.stream().map(deck -> this.deckToDTO(deck)).collect(Collectors.toList());

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
        deckRepository.deleteById(id);
    }

    public Deck getDeckBy(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void save(DeckRequestDTO deckRequest) throws CardNotFoundException {
        List<Card> cardList = new ArrayList<>();

        for(Long cardId : deckRequest.getCardListId()){
            Card card = cardRepository.findById(cardId).orElseThrow(() -> new NotFoundException("Card not found with id: " + cardId));
            if(card.correctCard()) {
                cardList.add(card); //validation of the card if it has all the attributes
            }else{
//                System.out.println("Card does not have all the attributes");//TODO Message for response
                //card not available message showned
                throw new CardNotFoundException("card does not have all the attributes needed");

            }

        }

        Deck deck = new Deck(deckRequest.getName(), cardList);

        deckRepository.save(deck);

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Deck getDeckByName(String deck) throws DeckNotFoundException {
        return deckRepository.findByName(deck)
                .orElseThrow(() -> new DeckNotFoundException(deck));
    }

    @Override
    public void updateDeck(Long deckId, DeckRequestDTO deckRequest) throws DeckNotFoundException, CardNotFoundException {

        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new NotFoundException(
                        "Deck not found with id: " + deckId.toString())
                );

        List<Card> cardList = new ArrayList<>();

        for(Long cardId : deckRequest.getCardListId()){
            Card card = cardRepository.findById(cardId).orElseThrow(() -> new NotFoundException("Card not found with id: " + cardId));
            if(card.correctCard()) {
                cardList.add(card); //validation of the card if it has all the attributes
            }else{
                throw new CardNotFoundException("card does not have all the attributes needed");
            }

        }

        deck.setCardList(cardList);
        deck.setName(deckRequest.getName());
        deckRepository.update(deck);

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





    private DeckDTO deckToDTO(Deck deck){

        List<CardDTO> deckDTOList = new ArrayList<>();

        for(Card card : deck.getCardList()){
            CardDTO cardDTO = cardToDTO(card);
            deckDTOList.add(cardDTO);
        }

        return new DeckDTO(deck.getName(), deckDTOList);
    }

    //TODO: falta implementar
    private static CardDTO cardToDTO(Card card){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId().toString());
        cardDTO.setName(card.getName());
        AppearenceStatDTO appearenceStatDTO = new AppearenceStatDTO();//TODO ADD ALL THE ATTRIBUTES to the initial DTO
        PowerStatDTO powerStatDTO = new PowerStatDTO();
        powerStatDTO.setPower(card.getPower().toString());
        powerStatDTO.setCombat(card.getCombat().toString());
        powerStatDTO.setIntelligence(card.getIntelligence().toString());
        powerStatDTO.setStrength(card.getStrength().toString());
        powerStatDTO.setSpeed(card.getSpeed().toString());

        cardDTO.setAppearance(appearenceStatDTO);
        cardDTO.setPowerstats(powerStatDTO);
        ImageDTO imageDTO = new ImageDTO();//TODO

        return cardDTO;
    }

}
