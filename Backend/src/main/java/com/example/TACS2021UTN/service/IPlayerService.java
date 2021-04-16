package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;

import java.util.List;

public interface IPlayerService  {

    Player getPlayerByName(String name) throws PlayerNotFoundException;

    Player createPlayer(Player player);

    Player getPlayerById(Long id) throws PlayerNotFoundException;

    void delete(Player player) ;;
}
