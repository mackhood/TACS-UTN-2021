package com.example.TACS2021UTN.repositories.game;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Player;

import java.time.LocalDate;
import java.util.List;

public interface IGameRepository {

    List<Game> getAllGames();
    Game createNewGame(Player creator, Player challenged, Deck deck);
    Game leaveGame(Long id, Player player);
    List<Game> showGamesByFilters(LocalDate from, LocalDate to);

}
