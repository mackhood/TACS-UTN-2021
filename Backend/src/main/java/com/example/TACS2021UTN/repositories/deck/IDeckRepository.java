package com.example.TACS2021UTN.repositories.deck;

import com.example.TACS2021UTN.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDeckRepository extends JpaRepository<Deck, Long>{

    Optional<Deck> findByName(String deck);
    //List<Deck> getAllDecks();
}
