package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.exceptions.BadDatesInserted;
import com.example.TACS2021UTN.functions.DateAnalizer;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.service.game.IGameService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    private final IGameService service;

    public GameController(IGameService gameService){
        this.service = gameService;
    }


    @PostMapping("/games")
    public ResponseEntity<GameDTO> createNewGame(@RequestBody GameDTO gameDTO){
        service.createNewGame(gameDTO);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/games")
    public ResponseEntity<JSONWrapper> getAllGames(){
        return ResponseEntity.ok(new JSONWrapper<>((List<GameDTO>) service.getAllGames()));
    }


    @GetMapping("/games/{id}/replay")
    public ResponseEntity<JSONWrapper> getDuels(@PathVariable(value = "id") Long id) {

        User player1 = new User();
        player1.setId((long) 1);
        player1.setUsername("A");

        List<Duel> duels = new ArrayList<>();

        Duel duel1 = new Duel();
        duel1.setId((long) 1);
        duel1.setGanador(player1);

        Duel duel2 = new Duel();
        duel2.setId((long) 2);
        duel2.setGanador(player1);

        duels.add(duel1);
        duels.add(duel2);

        return ResponseEntity.ok(new JSONWrapper<>((List<Duel>) duels));
    }

    @PostMapping("/games/{id}/dropout")
    public ResponseEntity endGame(@PathVariable(value = "id") Long id) {
        User player1 = new User();
        player1.setId((long) 1);
        player1.setUsername("A");

        List<Duel> duels = new ArrayList<>();

        Duel duel1 = new Duel();
        duel1.setId((long) 1);
        duel1.setGanador(player1);

        Duel duel2 = new Duel();
        duel2.setId((long) 2);
        duel2.setGanador(player1);

        duels.add(duel1);
        duels.add(duel2);

        return ResponseEntity.status(204).build();
    }

    @GetMapping("/games/{id}/stats")
    public ResponseEntity getGameStats(){
        GameStatisticsDTO gameStatisticsDTO = new GameStatisticsDTO();
        gameStatisticsDTO.setCards_gained(1);
        gameStatisticsDTO.setCards_remaining(5);
        return ResponseEntity.ok(gameStatisticsDTO);
    }


    @GetMapping(value = "/gamesStats", params = {"from_date", "to_date"})
    public ResponseEntity getRangeOfGames(@RequestParam(name="from_date")@DateTimeFormat(pattern = "dd-MM-yyyy") String conditionByDateFrom,
                                              @RequestParam(name="to_date")@DateTimeFormat(pattern = "dd-MM-yyyy") String conditionByDateTo){
        LocalDate dateFrom = DateAnalizer.transformStringToLocalDate(conditionByDateFrom);
        LocalDate dateTo = DateAnalizer.transformStringToLocalDate(conditionByDateTo);



        if(!DateAnalizer.validateOrderOfDatesInserted(dateFrom, dateTo)){
            throw new BadDatesInserted("The dateFrom is greater than the dateTo");
        }

        return ResponseEntity.ok(service.showGamesByFilters(dateFrom, dateTo));

    }

    @GetMapping("/scoreboard")
    public ResponseEntity<JSONWrapper> getScoreboard(){
        //TODO ver el tema de victorias o derrotas de un jugador

        return ResponseEntity.ok().build();
    }


}