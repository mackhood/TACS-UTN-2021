package com.example.TACS2021UTN.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deck extends PersistantEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "deck_card",
            joinColumns = @JoinColumn(name = "deck_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cardList = new ArrayList<>();

    public void shuffle()
    {
        Collections.shuffle(this.cardList);
    }

    public List<List<Card>> splitInNParts(int n)
    {
        List<List<Card>> partitions = new ArrayList<>();

        int numberOfCards = this.cardList.size() / n;

        for(int i = 0; i < this.cardList.size(); i += numberOfCards){
            partitions.add(this.cardList.subList(i, i + numberOfCards));
        }

        return partitions;
    }

    public Integer getNumberOfCards(){
        return cardList.size();
    }
}
