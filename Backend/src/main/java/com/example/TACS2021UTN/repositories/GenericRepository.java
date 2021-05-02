package com.example.TACS2021UTN.repositories;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.PersistantEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class GenericRepository<T extends PersistantEntity> {

    protected List<T> database;

    public GenericRepository(){
        this.database = load();
    }

    protected abstract List<T> load();

    private Long nextId(){
        T tMax = database.stream().max(Comparator.comparing(PersistantEntity::getId)).orElse(null);
        long maxId = 0;
        if(tMax != null)
            maxId = tMax.getId();
        return maxId;
    }

    public void save(T deckToSave) {
        deckToSave.setId(nextId());
        database.add(deckToSave);
    }

    public Optional<T> findById(Long id){
        return database.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        this.database.removeIf(t -> t.getId().equals(id));
    }

    public List<T> findAll() {
        return database;
    }


}