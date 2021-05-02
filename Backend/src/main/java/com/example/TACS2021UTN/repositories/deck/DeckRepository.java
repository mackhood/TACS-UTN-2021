package com.example.TACS2021UTN.repositories.deck;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.PersistantEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class DeckRepository implements IDeckRepository{

    private List<Deck> database;

    public DeckRepository(){
        this.database = load();
    }

    public Optional<Deck> findByName(String deck) {
        return Optional.empty();
    }

    @Override
    public Optional<Deck> findById(Long id) {
        return findInDatabaseById(id);
    }

    @Override
    public void save(Deck deckToSave) {
        Deck maxId = getDatabase().stream().max(Comparator.comparing(PersistantEntity::getId)).orElse(new Deck());

        if(maxId.getId() == null){
            deckToSave.setId(1L);
        }
        else{
            deckToSave.setId(maxId.getId());
        }
        database.add(deckToSave);
    }

    @Override
    public void deleteById(Long id) {
        this.database.removeIf(deck -> deck.getId().equals(id));
    }

    @Override
    public List<Deck> findAll() {
        return this.getDatabase();
    }



    @Override
    public void delete(Deck deck) {

    }

    private Optional<Deck> findInDatabaseById(Long id){
        List<Deck> list = getDatabase();
        return list.stream().filter(deck -> deck.getId().equals(id)).findFirst();
    }



    /*
        @Override
        public List<Deck> getAllDecks() {
            return this.database;
        }
    */



    /*
    private List<Deck> load(){

        File file = null;

        try{

            file = ResourceUtils.getFile("classpath:decks.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<Deck>> typeReference = new TypeReference<List<Deck>>() { };
        List<Deck> listDecks = null;

        try {
            listDecks = objectMapper.readValue(file,typeReference);

        } catch (IOException exception){
            exception.printStackTrace();
        }
        return listDecks;

    }
    */
    private List<Deck> load(){
        List<Deck> random = new ArrayList<>();
        Deck deck1 = new Deck("Mazo 1", new ArrayList<>());
        deck1.setId(1L);
        random.add(deck1);
        Deck deck2 = new Deck("Mazo 2", new ArrayList<>());
        deck2.setId(2L);
        random.add(deck2);
        Deck deck3 = new Deck("Mazo 3", new ArrayList<>());
        deck3.setId(3L);
        random.add(deck3);
        Deck deck4 = new Deck("Mazo 4", new ArrayList<>());
        deck4.setId(4L);
        random.add(deck4);
        Deck deck5 = new Deck("Mazo 5", new ArrayList<>());
        deck5.setId(5L);
        random.add(deck5);
        Deck deck6 = new Deck("Mazo 6", new ArrayList<>());
        deck6.setId(6L);
        random.add(deck6);

        return random;

    }

    private List<Deck> getDatabase(){
        return this.database;
    }

}
