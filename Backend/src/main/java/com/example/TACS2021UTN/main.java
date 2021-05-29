package com.example.TACS2021UTN;

import com.example.TACS2021UTN.DTO.*;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Game;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IllegalAccessException {

        ModelMapper modelMapper = new ModelMapper();

        PlayerGameDTO creator = new PlayerGameDTO("ndeah", 1,1, "true");
        PlayerGameDTO challenged = new PlayerGameDTO("ndeah challenged", 1,1, "false");
        GameDTO gameDTO = new GameDTO(creator, challenged, "deckardium", 40);

        Game game = modelMapper.map(gameDTO, Game.class);

        System.out.println(game.getChallenged());



    }
/*
    public boolean checkNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
        return true;
    }
*/
}
