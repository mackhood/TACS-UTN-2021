package com.example.TACS2021UTN.repositories;

import com.example.TACS2021UTN.models.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);
}
