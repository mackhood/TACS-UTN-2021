package com.example.TACS2021UTN.repository;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayerRepository extends JpaRepository<Player, Long> {
}
