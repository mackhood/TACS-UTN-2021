package com.example.TACS2021UTN.repositories.game;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.repositories.GenericRepository;
import com.example.TACS2021UTN.repositories.deck.DeckRepository;
import com.example.TACS2021UTN.repositories.user.UserRepository;
import com.example.TACS2021UTN.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository extends GenericRepository<Game> implements IGameRepository {

    protected List<Game> load(){
        return new ArrayList<Game>();
    }

    @Override
    public Game leaveGame(Long id, User player) {
        List<Game> games = this.database;
        Game gameSearched = null;
        for(Game game : games){
            if(game.getIdFromCreator().equals(player.getId()) || game.getIdFromChallenged().equals(player.getId())){
                gameSearched = game;
                break;
            }
        }

        if(gameSearched != null){
            playerLeavesGame(player, gameSearched);
        }

        return gameSearched;
    }

    @Override
    public List<Game> showGamesByFilters(LocalDate from, LocalDate to) {
        List<Game> games = this.database;
        List<Game> filtered = new ArrayList<>();

        for(Game game : games){
            if(accomplishWithFilters(game, from, to)){
                filtered.add(game);
            }
        }

        return filtered;
    }

    private void playerLeavesGame(User player, Game game){
        if(game.getIdFromCreator().equals(player.getId())){
            //game.setState(new Finished(game.getChallenged().getPlayer()));
        }
        else{
            //game.setState(new Finished(game.getCreator().getPlayer()));
        }
    }

    private boolean accomplishWithFilters(Game game, LocalDate from, LocalDate to){
        return (game.getDateOfCreation().isBefore(from) ||
                game.getDateOfCreation().equals(from)) &&
                ((game.getDateOfCreation().isAfter(to)) ||
                        game.getDateOfCreation().equals(to));
    }
}
