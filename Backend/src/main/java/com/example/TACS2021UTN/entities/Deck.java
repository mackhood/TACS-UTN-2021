package com.example.TACS2021UTN.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class Deck {

    private List<Card> cardList = new ArrayList<>();



    public Deck(List<Card> cardList) {
        this.cardList = cardList;
    }


}
