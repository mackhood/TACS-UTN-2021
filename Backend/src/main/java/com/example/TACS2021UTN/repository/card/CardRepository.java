package com.example.TACS2021UTN.repository.card;


import com.example.TACS2021UTN.DTO.CardDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class CardRepository implements ICardRepository {


    private List<CardDTO> database;


    public CardRepository() {
        this.database = load();
    }

    private List<CardDTO> load(){

        File file = null;

        try{

            file = ResourceUtils.getFile("classpath:cards.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<CardDTO>> typeReference = new TypeReference<List<CardDTO>>() { };
        List<CardDTO> listCards = null;

        try {
            listCards = objectMapper.readValue(file,typeReference);

        } catch (IOException exception){
            exception.printStackTrace();
        }
        return listCards;

    }

    @Override
    public List<CardDTO> getAllCards() {
        return database;
    }
}
