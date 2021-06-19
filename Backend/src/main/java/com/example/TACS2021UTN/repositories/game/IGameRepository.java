package com.example.TACS2021UTN.repositories.game;

import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IGameRepository extends JpaRepository<Game, Long> {
    List<Game> findAll();
    Optional<Game> findById(Long id);
    //void save(Game newGame);
    //void update(Game game);
    //Game leaveGame(Long id, User player);
    @Query("from Game g where (g.dateOfCreation >= :from and g.dateOfCreation <= :to)")
    List<Game> showGamesByFilters(LocalDate from, LocalDate to);
}
