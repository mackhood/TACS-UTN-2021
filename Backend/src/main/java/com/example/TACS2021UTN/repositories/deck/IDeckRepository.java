package com.example.TACS2021UTN.repositories.deck;

import com.example.TACS2021UTN.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IDeckRepository extends JpaRepository<Deck, Long> {
    Optional<Deck> findByName(String name);
    Optional<Deck> findById(Long id);
    //void save(Deck deck);
    @Transactional
    @Modifying
    @Query("delete FROM Deck d where d.id=:id")
    void deleteById(Long id);
    //Boolean deleteById(Long id);
    //void update(Deck deck);
}

