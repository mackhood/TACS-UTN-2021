package com.example.TACS2021UTN.repositories.deck;

import com.example.TACS2021UTN.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IDeckRepository {

    Optional<Deck> findByName(String deck);
    Optional<Deck> findById(Long id);
    List<Deck> findAll();
    void save(Deck deck);

    void delete(Deck deck);

    Boolean deleteById(Long id);

    void update(Deck deck);
}
