package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.GameDTO;
import com.example.TACS2021UTN.DTO.GameStatisticsDTO;
import com.example.TACS2021UTN.DTO.GamesStatisticsDTO;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Player;
import com.example.TACS2021UTN.repositories.IPlayerRepository;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import com.example.TACS2021UTN.repositories.game.IGameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService implements IGameService {

    private ModelMapper modelMapper;

    private IGameRepository gameRepository;
    private IPlayerRepository playerRepository;
    private IDeckRepository deckRepository;

    public GameService(IGameRepository gameRepository, IPlayerRepository playerRepository, IDeckRepository deckRepository, ModelMapper modelMapper){
        this.deckRepository = deckRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.getAllGames();
        return games.stream().map(game -> fromGameToDTO(game)).collect(Collectors.toList());
    }

    @Override
    public GameDTO createNewGame(GameDTO gameDTO) {
        Optional<Player> aCreator = playerRepository.findById(gameDTO.getCreatorId());
        Optional<Player> aChallenged = playerRepository.findById(gameDTO.getChallengedId());
        Optional<Deck> aDeck = deckRepository.findById(gameDTO.getDeckId());

        if(true){
            // TODO validateAllParameters(aCreator, aChallenged, aDeck)
        }

        Game gameResponse = gameRepository.createNewGame(aCreator.get(), aChallenged.get(), aDeck.get());
        return fromGameToDTO(gameResponse);

    }

    @Override
    public void leaveGame(Long id, Player player) {
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
        return new GameDTO(
                game.getIdFromCreator(),
                game.getIdFromChallenged(),
                game.getDeck().getId()
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
