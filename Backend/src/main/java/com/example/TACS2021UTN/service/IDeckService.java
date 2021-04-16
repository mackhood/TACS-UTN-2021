package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;

public interface IDeckService {

    public Deck getDeckByName(String name) throws  DeckNotFoundException;

}
