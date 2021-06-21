package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface IGameService {

    GameDTO createNewGame(NewGameDTO gameDTO, String creatorUsername);
    GameDTO findById(Long id);
    //void leaveGame(Long id, User player);
    GamesStatisticsDTO showGamesByFilters(LocalDate from, LocalDate to);
    List<GameDTO> getAllGames(Pageable paging);
    DuelDTO generateDuel(Long gameId, String playerUsername, DuelRequestDTO duelRequestDTO) throws NonPlayebleGameStateException, UserWithoutTurnException;
    List<DuelDTO> getAllDuels(Long id);
    CardDTO showCardForDuel(Long id, String playerUsername);
}