package com.example.TACS2021UTN.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deck extends PersistantEntity{

    private String name;
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
            partitions.add(this.cardList.subList(i, i + numberOfCards - 1));
        }

        return partitions;
    }
}
