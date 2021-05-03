package com.example.TACS2021UTN.repositories.game;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IGameRepository {

    List<Game> getAllGames();
    Optional<Game> findById(Long id);
    Game createNewGame(User creator, User challenged, Deck deck);
    Game leaveGame(Long id, User player);
    List<Game> showGamesByFilters(LocalDate from, LocalDate to);

}
