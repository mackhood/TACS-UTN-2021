package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.GameDTO;
import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.DTO.GamesStatisticsDTO;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Player;

import java.time.LocalDate;
import java.util.List;

public interface IGameService {

    GameDTO createNewGame(GameDTO gameDTO);
    GameDTO findById(Long id);
    void leaveGame(Long id, Player player);
    GamesStatisticsDTO showGamesByFilters(LocalDate from, LocalDate to);
    List<GameDTO> getAllGames();
}