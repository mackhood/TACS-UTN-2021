package com.example.TACS2021UTN.configs;

import com.example.TACS2021UTN.DTO.DuelDTO;
import com.example.TACS2021UTN.DTO.DuelResultDTO;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.DuelResult;
import com.example.TACS2021UTN.models.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Duel.class, DuelDTO.class)
        .addMappings(mapper -> mapper.<String>map(src -> src.getAttribute().getName(), DuelDTO::setAttribute))
        .addMappings(mapper -> mapper.<String>map(src -> src.getWinner().getUsername(), (dest, user) -> dest.getResult().setWinner(user)))
        .addMappings(mapper -> mapper.<Integer>map(src -> src.getGame().cardsLeft(), DuelDTO::setCardsLeft));

        return modelMapper;
    }

}
