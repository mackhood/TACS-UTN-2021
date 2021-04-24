package com.example.TACS2021UTN.service.player;

import com.example.TACS2021UTN.models.user.Player;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;

public interface IPlayerService  {

    Player getPlayerByName(String name) throws PlayerNotFoundException;

    Player createPlayer(Player player);

    Player getPlayerById(Long id) throws PlayerNotFoundException;

    void delete(Player player) ;
}
