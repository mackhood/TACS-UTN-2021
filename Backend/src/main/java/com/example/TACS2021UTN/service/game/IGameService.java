package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Player;

import java.time.LocalDate;

public interface IGameService {

    Game createNewGame(Player a, Player b, Deck deck);
    void leaveGame(Long id, Player player);
    GameStatisticsDTO showGamesByFilters(LocalDate from, LocalDate to);
}