package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;

import java.util.List;

public interface IPlayerService  {

    public Player getPlayerByName(String name) throws PlayerNotFoundException;
}
