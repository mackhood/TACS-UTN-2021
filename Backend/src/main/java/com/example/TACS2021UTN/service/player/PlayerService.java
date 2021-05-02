package com.example.TACS2021UTN.service.player;

import com.example.TACS2021UTN.models.user.Player;
import com.example.TACS2021UTN.exceptions.PlayerNotFoundException;

import com.example.TACS2021UTN.repositories.IPlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService {

    private final IPlayerRepository repository;

    public PlayerService(IPlayerRepository playerRepository){
        this.repository = playerRepository;
    }

    public Player getPlayerById(Long playerId) throws PlayerNotFoundException {
        return repository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId.toString()));
    }

    @Override
    public void delete(Player player) {
        repository.delete(player);
    }

    public Player getPlayerByName(String name) throws PlayerNotFoundException {
        return repository.findByName(name)
                .orElseThrow(() -> new PlayerNotFoundException(name));
    }


    public Player createPlayer(Player player)  {
        return repository.save(player);

    }


}
