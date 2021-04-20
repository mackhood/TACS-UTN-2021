package com.example.TACS2021UTN.DTO;

import com.example.TACS2021UTN.entities.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeckDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private List<Card> cardList;

}
