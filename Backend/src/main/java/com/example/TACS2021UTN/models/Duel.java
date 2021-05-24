package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.attribute.Attribute;
import com.example.TACS2021UTN.models.user.PlayerGame;
import com.example.TACS2021UTN.models.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Duel extends PersistantEntity{

    private PlayerGame winner;
    private Card creatorCard;
    private Card challengedCard;
    private Attribute attribute;


}
