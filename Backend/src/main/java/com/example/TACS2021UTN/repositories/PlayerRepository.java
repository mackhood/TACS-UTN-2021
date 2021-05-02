package com.example.TACS2021UTN.repositories;

import com.example.TACS2021UTN.models.user.Player;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlayerRepository implements IPlayerRepository{
    @Override
    public Optional<Player> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Player save(Player player) {
        return null;
    }

    @Override
    public void delete(Player player) {

    }
}
