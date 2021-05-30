package com.example.TACS2021UTN.DTO;

import com.example.TACS2021UTN.models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DuelDTO {
    public Long id;
    public Integer cardsLeft;
    public CardDTO creatorCard;
    public CardDTO challengedCard;
    public String attribute;
    public DuelResultDTO result;
}
