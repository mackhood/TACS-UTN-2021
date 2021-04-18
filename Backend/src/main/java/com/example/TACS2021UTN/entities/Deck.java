package com.example.TACS2021UTN.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter

public class Deck {

    private Long id;
    private String name;
    private List<Card> cardList = new ArrayList<>();

    public Deck(String name, List<Card> cardList) {
        this.name = name;
        this.cardList = cardList;
    }

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
