package com.example.TACS2021UTN.DTO;

import com.example.TACS2021UTN.entities.user.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ScoreboardDTO {

    Player player;
    GamesStatisticsDTO gamesStatisticsDTO;

}
