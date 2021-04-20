package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;
import com.example.TACS2021UTN.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    @GetMapping("/players")
    public List<Player> getPlayers() throws PlayerNotFoundException {

        List<Player> players = new ArrayList<>();

        Player player1 = new Player();
        player1.setId((long) 1);
        player1.setName("player 1");
        players.add(player1);

        Player player2 = new Player();
        player2.setId((long) 2);
        player2.setName("player 2");
        players.add(player2);

        return players;
        //return this.playerService.getPlayerById(id);
    }


    @Autowired
    private IPlayerService playerService;

    /*@GetMapping("/players/{name}")
    public Player getPlayerByName(@PathVariable(value = "name") String name) throws PlayerNotFoundException {

        return this.playerService.getPlayerByName(name);
    }*/


    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable(value = "id") Long id) throws PlayerNotFoundException {

        Player player1 = new Player();
        player1.setId(id);
        player1.setName("player 1");

        return player1;
        //return this.playerService.getPlayerById(id);
    }


    @PostMapping("/players")
    public Player createPlayer(@Valid @RequestBody Player player) {

        return player;

        //return playerService.createPlayer(player);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable(value = "id") Long playerId) throws PlayerNotFoundException {
        //Player player = playerService.getPlayerById(playerId);

        //playerService.delete(player);

        return ResponseEntity.ok().build();
    }

}
