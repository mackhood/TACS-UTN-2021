package com.example.TACS2021UTN.DTO;

import com.example.TACS2021UTN.models.user.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreboardDTO {

    Player player;
    GamesStatisticsDTO gamesStatisticsDTO;

}
