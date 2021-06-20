package com.example.TACS2021UTN.repositories.game;

import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IGameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findById(Long id);
    @Query("from Game g where (g.dateOfCreation >= :from and g.dateOfCreation <= :to)")
    List<Game> showGamesByFilters(LocalDate from, LocalDate to);
}
