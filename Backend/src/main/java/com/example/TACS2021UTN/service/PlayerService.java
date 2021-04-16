package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;

import com.example.TACS2021UTN.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService{

    IPlayerRepository playerRepository;

    public Player getPlayerById(Long playerId) throws PlayerNotFoundException {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId.toString()));
    }

    public Player getPlayerByName(String name) throws PlayerNotFoundException {
        return playerRepository.findByName(name)
                .orElseThrow(() -> new PlayerNotFoundException(name));
    }


}
