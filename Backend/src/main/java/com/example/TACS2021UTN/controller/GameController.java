package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.GameDTO;
import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.Duel;
import com.example.TACS2021UTN.entities.Game;
import com.example.TACS2021UTN.entities.user.Admin;
import com.example.TACS2021UTN.entities.user.Player;
import com.example.TACS2021UTN.exceptions.BadDatesInserted;
import com.example.TACS2021UTN.functions.DateAnalizer;
import com.example.TACS2021UTN.service.game.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private IGameService service;

    @Autowired
    private ModelMapper modelMapper;

    private Player user;

    /*
    @PostMapping("/games")
    public GameDTO createNewGame(Player challenged, Deck deck){

        Game response = service.createNewGame(user, challenged, deck);
        GameDTO responseDTO = modelMapper.map(response, GameDTO.class);
        return responseDTO;
    }*/

    @PostMapping("/games")
    public Game createNewGame(GameDTO gameDTO){


        Player player1 = new Player();
        player1.setName("player A");
        player1.setId((long) gameDTO.creatorId);

        Player player2 = new Player();
        player2.setName("player A");
        player2.setId((long) gameDTO.challengedId);

        Deck deck = new Deck();
        deck.setId((long) gameDTO.deckId);

        Game game = new Game(player1, player2, deck);

        return game;
    }

    /*
    @PostMapping("/games/{id}/drop")
    public void leaveGame(@PathVariable Long id){
        service.leaveGame(id, user);
    }
    */

    @GetMapping("/games/{id}")
    public Game getGame(@PathVariable(value = "id") Long id) {

        Deck deck1 = new Deck("deck1", new ArrayList<>());

        Player player1 = new Player();
        player1.setId((long) 1);
        player1.setName("A");

        Player player2 = new Player();
        player2.setId((long) 2);
        player2.setName("A");

        Game game = new Game(player1, player2, deck1);

        return game;
        //return this.adminService.getAdminById(id);
    }

    @GetMapping("/games")
    public List<Game> getGames(){


        Deck deck1 = new Deck("deck1", new ArrayList<>());
        Deck deck2 = new Deck("deck2", new ArrayList<>());

        Player player1 = new Player();
        player1.setId((long) 1);
        player1.setName("A");

        Player player2 = new Player();
        player2.setId((long) 2);
        player2.setName("A");

        Game game1 = new Game(player1, player2, deck1);
        Game game2 = new Game(player2, player1, deck2);


        List<Game> games = new ArrayList<>();

        games.add(game1);
        games.add(game2);

        return games;

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
        //return this.adminService.getAdminById(id);
    }

    @GetMapping("/games/{id}/stats")
    public GameStatisticsDTO getGameStats(){
        GameStatisticsDTO gameStatisticsDTO = new GameStatisticsDTO();
        gameStatisticsDTO.setCards_gained(1);
        gameStatisticsDTO.setCards_remaining(5);
        return gameStatisticsDTO;
    }

    /*
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
     */

}