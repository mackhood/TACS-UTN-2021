package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import com.example.TACS2021UTN.repositories.game.IGameRepository;
import com.example.TACS2021UTN.repositories.user.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService implements IGameService {

    private ModelMapper modelMapper;

    private IGameRepository gameRepository;
    private IUserRepository userRepository;
    private IDeckRepository deckRepository;

    public GameService(IGameRepository gameRepository, IUserRepository userRepository, IDeckRepository deckRepository, ModelMapper modelMapper){
        this.deckRepository = deckRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.getAllGames();
        return games.stream().map(game -> fromGameToDTO(game)).collect(Collectors.toList());
    }

    @Override
    public GameDTO createNewGame(NewGameDTO gameDTO) {
        Optional<User> aCreator = userRepository.findByUserName(gameDTO.getCreatorUsername());
        Optional<User> aChallenged = userRepository.findByUserName(gameDTO.getChallengedUsername());
        Optional<Deck> aDeck = deckRepository.findByName(gameDTO.getDeckName());

        if(true){
            // TODO validateAllParameters(aCreator, aChallenged, aDeck)
        }

        Game gameResponse = gameRepository.createNewGame(aCreator.get(), aChallenged.get(), aDeck.get());
        return fromGameToDTO(gameResponse);

    }

    @Override
    public void leaveGame(Long id, User player) {
        gameRepository.leaveGame(id, player);
    }

    @Override
    public GamesStatisticsDTO showGamesByFilters(LocalDate from, LocalDate to) {
        List<Game> games = gameRepository.showGamesByFilters(from, to);
        GamesStatisticsDTO statistics = new GamesStatisticsDTO();
        for(Game game : games){
            if(game.getState().getClass().getSimpleName().equals("Created")){
                statistics.setCreated(statistics.getCreated() + 1);
            }
            else if(game.getState().getClass().getSimpleName().equals("InProgress")){
                statistics.setCreated(statistics.getInProgress() + 1);
            }
            else{
                statistics.setCreated(statistics.getFinished() + 1);
            }
        }
        return statistics;
    }

    GameDTO fromGameToDTO(Game game){
        PlayerGame creator = game.getCreator();
        PlayerGame challenged = game.getChallenged();
        return new GameDTO(
                new PlayerGameDTO(game.getUsernameFromCreator(),creator.getMainCards().size(),creator.getGainedCards().size(),creator.getIsMyTurn().toString()),
                new PlayerGameDTO(game.getUsernameFromChallenged(),challenged.getMainCards().size(),challenged.getGainedCards().size(),challenged.getIsMyTurn().toString()),
                game.getDeck().getName(),game.getDeck().getCardList().size()
        );
    }

    public GameDTO findById(Long id)
    {
        Optional<Game> game = gameRepository.findById(id);
        if(!game.isPresent()){
          //TODO excepcion ?
        }

        return fromGameToDTO(game.get());
    }




}
