package com.example.TACS2021UTN.repository.deck;

import com.example.TACS2021UTN.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDeckRepository extends JpaRepository<Deck, Long>{

    Optional<Deck> findByName(String deck);
    //List<Deck> getAllDecks();
}
