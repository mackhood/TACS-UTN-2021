package com.example.TACS2021UTN.repositories;

import com.example.TACS2021UTN.models.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlayerRepository {
    Optional<Player> findByName(String name);
    Optional<Player> findById(Long id);
    Player save(Player player);
    void delete(Player player);
}
