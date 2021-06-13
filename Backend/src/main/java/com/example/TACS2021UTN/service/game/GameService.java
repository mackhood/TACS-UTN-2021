package com.example.TACS2021UTN.service.game;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.exceptions.NonPlayebleGameStateException;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.exceptions.UserWithoutTurnException;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.repositories.attributes.IAttributeRepository;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import com.example.TACS2021UTN.repositories.game.IGameRepository;
import com.example.TACS2021UTN.repositories.user.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService implements IGameService {

    private ModelMapper modelMapper;
    private IGameRepository gameRepository;
    private IUserRepository userRepository;
    private IDeckRepository deckRepository;
    private IAttributeRepository attributeRepository;

    public GameService(IGameRepository gameRepository, IUserRepository userRepository, IDeckRepository deckRepository,
                       ModelMapper modelMapper, IAttributeRepository attributeRepository){
        this.deckRepository = deckRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(game -> modelMapper.map(game, GameDTO.class)).collect(Collectors.toList());
    }

    @Override
    public GameDTO createNewGame(NewGameDTO gameDTO) {
        User creator = userRepository.findByUserName(gameDTO.getCreatorUsername()).orElseThrow(() -> new NotFoundException("User not found: " + gameDTO.getCreatorUsername()));
        User challenged = userRepository.findByUserName(gameDTO.getChallengedUsername()).orElseThrow(() -> new NotFoundException("User not found" + gameDTO.getChallengedUsername()));
        Deck deck = deckRepository.findById(gameDTO.getDeckID()).orElseThrow(() -> new NotFoundException("Deck not found with ID: " + gameDTO.getDeckID()));

        Game newGame = new Game(creator, challenged, deck);
        gameRepository.save(newGame);
        newGame.startGame();

        GameDTO game = modelMapper.map(newGame, GameDTO.class);
        return game;
    }

    @Override
    public DuelDTO generateDuel(Long gameId, String playerUsername , DuelRequestDTO duelRequestDTO) throws NonPlayebleGameStateException, UserWithoutTurnException {

        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("Game not found with ID" + gameId));
        Attribute attribute = attributeRepository.findByName(duelRequestDTO.attribute)
                .orElseThrow(()-> new NotFoundException("Attribute not found: " + duelRequestDTO.attribute));
        User user = userRepository.findByUserName(playerUsername).get();

        if(!game.userIsInGame(user))
            throw new NotFoundException("Game not found with ID: " + gameId);

        Duel duel = game.play(user, attribute);
        //gameRepository.update(game);

        return modelMapper.map(duel, DuelDTO.class);
    }

    @Override
    public List<DuelDTO> getAllDuels(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("Game not found with ID" + gameId));
        List<Duel> duels = game.getDuels();
        return duels.stream().map(duel -> modelMapper.map(duel, DuelDTO.class)).collect(Collectors.toList());
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

    public GameDTO findById(Long id)
    {
        Game game = gameRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found with ID" + id));
        return modelMapper.map(game, GameDTO.class);
    }

}
