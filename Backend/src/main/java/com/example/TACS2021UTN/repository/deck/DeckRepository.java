package com.example.TACS2021UTN.repository.deck;

import com.example.TACS2021UTN.entities.Deck;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class DeckRepository {

    private List<Deck> database;

    public DeckRepository(){
        this.database = load();
    }

    public Optional<Deck> findByName(String deck) {
        return Optional.empty();
    }
/*
    @Override
    public List<Deck> getAllDecks() {
        return this.database;
    }
*/
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

}
