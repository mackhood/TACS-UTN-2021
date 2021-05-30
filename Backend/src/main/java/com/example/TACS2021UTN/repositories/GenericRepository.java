package com.example.TACS2021UTN.repositories;

import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.PersistantEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericRepository<T extends PersistantEntity> {

    private final Integer maxSize = 3;
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
        return maxId + 1;
    }

    public void save(T entity) {
        entity.setId(nextId());
        database.add(entity);
    }

    public void update(T entity){
        deleteById(entity.getId());
        database.add(entity);
    }

    public Optional<T> findById(Long id){
        return database.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Boolean deleteById(Long id) {
        return this.database.removeIf(t -> t.getId().equals(id));
    }

    public List<T> findAll() {
        return database;
    }
}
