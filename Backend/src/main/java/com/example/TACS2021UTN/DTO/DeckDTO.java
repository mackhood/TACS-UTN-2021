package com.example.TACS2021UTN.DTO;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeckDTO {

    private Long id;
    private String name;
    private List<CardDTO> cardList;

    public DeckDTO(String name, List<CardDTO> cardList){
        this.name = name;
        this.cardList = cardList;
    }

}
