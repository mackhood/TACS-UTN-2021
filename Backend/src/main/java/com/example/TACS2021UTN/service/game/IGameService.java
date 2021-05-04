package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.GameDTO;
import com.example.TACS2021UTN.DTO.GamesStatisticsDTO;
import com.example.TACS2021UTN.models.user.User;

import java.time.LocalDate;
import java.util.List;

public interface IGameService {

    GameDTO createNewGame(GameDTO gameDTO);
    GameDTO findById(Long id);
    void leaveGame(Long id, User player);
    GamesStatisticsDTO showGamesByFilters(LocalDate from, LocalDate to);
    List<GameDTO> getAllGames();
}