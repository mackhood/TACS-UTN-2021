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
        return random;
    }

    @Override
    public void delete(Deck deck) {
        
    }

    public Optional<Deck> findByName(String deck) {
        return Optional.empty();
    }





}
