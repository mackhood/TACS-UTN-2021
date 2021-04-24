package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Player;
import com.example.TACS2021UTN.repositories.game.IGameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private ModelMapper modelMapper;

    private IGameRepository gameRepository;

    public GameService(IGameRepository gameRepo){
        this.gameRepository = gameRepo;
    }

    @Override
    public Game createNewGame(Player creator, Player challenged, Deck deck) {
        return gameRepository.createNewGame(creator, challenged, deck);

    }

    @Override
    public void leaveGame(Long id, Player player) {
        gameRepository.leaveGame(id, player);
    }

    //ver una mejor manera de realizar ese if, lo hice para salir del paso
    @Override
    public GameStatisticsDTO showGamesByFilters(LocalDate from, LocalDate to) {
        List<Game> games = gameRepository.showGamesByFilters(from, to);
        GameStatisticsDTO statistics = new GameStatisticsDTO();
        for(Game game : games){
            /*if(game.getState().getClass().getSimpleName().equals("Created")){
                statistics.setCreated(statistics.getCreated() + 1);
            }
            else if(game.getState().getClass().getSimpleName().equals("InProgress")){
                statistics.setCreated(statistics.getInProgress() + 1);
            }
            else{
                statistics.setCreated(statistics.getFinished() + 1);
            }*/
        }
        return statistics;
    }
}
