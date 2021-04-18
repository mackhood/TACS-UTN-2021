package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;
import com.example.TACS2021UTN.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping("/players/{name}")
    public Player getPlayerByName(@PathVariable(value = "name") String name) throws PlayerNotFoundException {

        return this.playerService.getPlayerByName(name);
    }


    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable(value = "id") Long id) throws PlayerNotFoundException {

        return this.playerService.getPlayerById(id);
    }


    @PostMapping("/player")
    public Player createPlayer(@Valid @RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable(value = "id") Long playerId) throws PlayerNotFoundException {
        Player player = playerService.getPlayerById(playerId);

        playerService.delete(player);

        return ResponseEntity.ok().build();
    }

}
