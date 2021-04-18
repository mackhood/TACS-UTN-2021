package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.GameDTO;
import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.BadDatesInserted;
import com.example.TACS2021UTN.functions.DateAnalizer;
import com.example.TACS2021UTN.service.game.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class GameController {

    @Autowired
    private IGameService service;

    @Autowired
    private ModelMapper modelMapper;

    private Player user;

    @PostMapping("/game")
    public GameDTO createNewGame(Player challenged, Deck deck){
        Game response = service.createNewGame(user, challenged, deck);
        GameDTO responseDTO = modelMapper.map(response, GameDTO.class);
        return responseDTO;
    }

    @PostMapping("/games/{id}/drop")
    public void leaveGame(@PathVariable Long id){
        service.leaveGame(id, user);
    }

    @GetMapping(value = "/games", params = {"from_date", "to_date"})
    public GameStatisticsDTO getRangeOfGames(@RequestParam(name="from_date")@DateTimeFormat(pattern = "dd-MM-yyyy") String conditionByDateFrom,
                                             @RequestParam(name="to_date")@DateTimeFormat(pattern = "dd-MM-yyyy") String conditionByDateTo){
        LocalDate dateFrom = DateAnalizer.transformStringToLocalDate(conditionByDateFrom);
        LocalDate dateTo = DateAnalizer.transformStringToLocalDate(conditionByDateTo);

        if(DateAnalizer.validateOrderOfDatesInserted(dateFrom, dateTo)){
            //List<Game> games = service.showGamesByFilters(dateFrom, dateTo);
            //List<GameDTO> gamesDTO = games.stream().map(game -> modelMapper.map(game, GameDTO.class)).collect(Collectors.toList());
            //return gamesDTO;

            return service.showGamesByFilters(dateFrom, dateTo);
        }
        else{
            throw new BadDatesInserted("The dateFrom is greater than the dateTo");
        }
    }

}