package com.example.TACS2021UTN.repositories.deck;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.PersistantEntity;
import com.example.TACS2021UTN.repositories.GenericRepository;
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
public class DeckRepository extends GenericRepository<Deck> implements IDeckRepository{


    public DeckRepository(){
        super();
    }

    @Override
    protected List<Deck> load(){
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

    @Override
    public void delete(Deck deck) {

    }

    public Optional<Deck> findByName(String deck) {
        return Optional.empty();
    }





}
