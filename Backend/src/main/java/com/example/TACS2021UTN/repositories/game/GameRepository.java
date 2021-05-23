package com.example.TACS2021UTN.repositories.game;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
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
public class GameRepository implements IGameRepository {

    private List<Game> database;

    public GameRepository() {
        this.database = load();
        //TODO i think the only game required should be load up in the memory
    }

    @Override
    public List<Game> getAllGames() {
        return database;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Game createNewGame(User creator, User challenged, Deck deck) {
        Game newGame = new Game(creator, challenged, deck);
        database.add(newGame);
        return newGame;
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

    private List<Game> load(){

/*        File file = null;

        try{

            file = ResourceUtils.getFile("classpath:games.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<Game>> typeReference = new TypeReference<List<Game>>() { };
        List<Game> listCards = null;

        try {
            listCards = objectMapper.readValue(file,typeReference);

        } catch (IOException exception){
            exception.printStackTrace();
        }
        return listCards;*/
        List<Game> games = new ArrayList<>();

        UserRepository userRepository = new UserRepository();
        Optional<User> user = userRepository.findByUserName("admin");
        Optional<User> user2 = userRepository.findByUserName("player");
        DeckRepository deckRepository = new DeckRepository();
        List<Deck> decks = deckRepository.findAll();
        Game game = new Game(user.get(),user2.get(),decks.get(0));
        games.add(game);
        return games;
    }
}
