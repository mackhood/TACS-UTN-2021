package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.GameDTO;
import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.DTO.GamesStatisticsDTO;
import com.example.TACS2021UTN.DTO.ScoreboardDTO;
import com.example.TACS2021UTN.exceptions.BadDatesInserted;
import com.example.TACS2021UTN.functions.DateAnalizer;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Player;
import com.example.TACS2021UTN.service.game.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    private final IGameService service;

    public GameController(IGameService gameService){
        this.service = gameService;
    }


    @PostMapping("/games")
    public GameDTO createNewGame(@RequestBody GameDTO gameDTO){
        return service.createNewGame(gameDTO);
    }

    @GetMapping("/games/{id}")
    public GameDTO getGame(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/games")
    public List<GameDTO> getAllGames(){
        return service.getAllGames();
    }


    @GetMapping("/games/{id}/replay")
    public List<Duel> getDuels(@PathVariable(value = "id") Long id) {

        Player player1 = new Player();
        player1.setId((long) 1);
        player1.setName("A");

        List<Duel> duels = new ArrayList<>();

        Duel duel1 = new Duel();
        duel1.setId((long) 1);
        duel1.setGanador(player1);

        Duel duel2 = new Duel();
        duel2.setId((long) 2);
        duel2.setGanador(player1);

        duels.add(duel1);
        duels.add(duel2);

        return duels;
    }

    @PostMapping("/games/{id}/dropout")
    public List<Duel> endGame(@PathVariable(value = "id") Long id) {
        Player player1 = new Player();
        player1.setId((long) 1);
        player1.setName("A");

        List<Duel> duels = new ArrayList<>();

        Duel duel1 = new Duel();
        duel1.setId((long) 1);
        duel1.setGanador(player1);

        Duel duel2 = new Duel();
        duel2.setId((long) 2);
        duel2.setGanador(player1);

        duels.add(duel1);
        duels.add(duel2);

        return duels;
    }

    @GetMapping("/games/{id}/stats")
    public GameStatisticsDTO getGameStats(){
        GameStatisticsDTO gameStatisticsDTO = new GameStatisticsDTO();
        gameStatisticsDTO.setCards_gained(1);
        gameStatisticsDTO.setCards_remaining(5);
        return gameStatisticsDTO;
    }


    @GetMapping(value = "/gamesStats", params = {"from_date", "to_date"})
    public GamesStatisticsDTO getRangeOfGames(@RequestParam(name="from_date")@DateTimeFormat(pattern = "dd-MM-yyyy") String conditionByDateFrom,
                                              @RequestParam(name="to_date")@DateTimeFormat(pattern = "dd-MM-yyyy") String conditionByDateTo){
        LocalDate dateFrom = DateAnalizer.transformStringToLocalDate(conditionByDateFrom);
        LocalDate dateTo = DateAnalizer.transformStringToLocalDate(conditionByDateTo);



        if(DateAnalizer.validateOrderOfDatesInserted(dateFrom, dateTo)){
            return service.showGamesByFilters(dateFrom, dateTo);
        }
        else{
            throw new BadDatesInserted("The dateFrom is greater than the dateTo");
        }

    }

    @GetMapping("/scoreboard")
    public List<ScoreboardDTO> getScoreboard(){
        //TODO ver el tema de victorias o derrotas de un jugador
        return null;
    }


}