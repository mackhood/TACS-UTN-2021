package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.exceptions.BadDatesInserted;
import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.functions.DateAnalizer;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.service.game.IGameService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
public class GameController extends BaseController{

    private final IGameService service;

    public GameController(IGameService gameService){
        this.service = gameService;
    }


    @PostMapping("/games")
    public ResponseEntity<GameDTO> createNewGame(@RequestBody NewGameDTO gameDTO){
        String creatorUsername = super.getAuthenticatedUsername();

        return ResponseEntity.status(201).body(service.createNewGame(gameDTO, creatorUsername));
    }

    @PostMapping("/games/{id}/duels")
    public ResponseEntity<?> generateDuel(@RequestBody DuelRequestDTO duelRequestDTO, @PathVariable Long id, Authentication user) throws NonPlayebleGameStateException, UserWithoutTurnException {

        return ResponseEntity.status(201).body(service.generateDuel(id, user.getName(), duelRequestDTO));
    }


    @GetMapping("/games/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/games")
    public ResponseEntity<JSONWrapper> getAllGames(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size){
        Pageable paging = PageRequest.of(page, getPageSize(size));

        return ResponseEntity.ok(new JSONWrapper<>(service.getAllGames(paging)));
    }


    @GetMapping("/games/{id}/replay")
    public ResponseEntity<JSONWrapper> getDuels(@PathVariable Long id) {
      return ResponseEntity.ok(new JSONWrapper<>(service.getAllDuels(id)));
    }

    @PostMapping("/games/{id}/dropouts")
    public ResponseEntity endGame(@PathVariable(value = "id") Long id) {
        User player1 = new User();
        player1.setId((long) 1);
        player1.setUsername("A");
        PlayerGame playerGame1 = new PlayerGame(player1,null);
        List<Duel> duels = new ArrayList<>();

        Duel duel1 = new Duel();
        duel1.setId((long) 1);

        Duel duel2 = new Duel();
        duel2.setId((long) 2);

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