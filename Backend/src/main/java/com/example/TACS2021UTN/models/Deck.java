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

    public List<List<Card>> divideDeck()
    {
        List<List<Card>> partitions = new ArrayList<>();

        int partitionSize = getNumberOfCards() / 2 ;

        for (int i=0; i< getNumberOfCards(); i += partitionSize) {
            partitions.add(this.cardList.subList(i, Math.min(i + partitionSize, getNumberOfCards())));
        }

        return partitions;
    }

    public Integer getNumberOfCards(){
        return cardList.size();
    }
}
