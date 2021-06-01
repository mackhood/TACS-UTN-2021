package com.example.TACS2021UTN.configs;

import com.example.TACS2021UTN.DTO.DuelDTO;
import com.example.TACS2021UTN.DTO.DuelResultDTO;
import com.example.TACS2021UTN.client.retrofit.superhero.entities.SuperHero;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Duel;
import com.example.TACS2021UTN.models.DuelResult;
import com.example.TACS2021UTN.models.user.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.criteria.CriteriaBuilder;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Duel.class, DuelDTO.class)
        .addMappings(mapper -> mapper.<String>map(src -> src.getAttribute().getName(), DuelDTO::setAttribute))
        .addMappings(mapper -> mapper.<String>map(src -> src.getWinner().getUsername(), (dest, user) -> dest.getResult().setWinner(user)))
        .addMappings(mapper -> mapper.<Integer>map(src -> src.getGame().cardsLeft(), DuelDTO::setCardsLeft));

        this.apiMappings(modelMapper);

        return modelMapper;
    }

    public void apiMappings(ModelMapper modelMapper){
        Converter<String, Integer> stringToInteger = ctx -> !isNull(ctx.getSource()) ? new Integer(ctx.getSource()) : null;

        modelMapper.createTypeMap(SuperHero.class, Card.class)
                .addMappings(mapper -> mapper.using(stringToInteger)
                        .<Integer>map(src -> src.getPowerstats().getStrength(), Card::setStrength))
                .addMappings(mapper -> mapper.using(stringToInteger)
                        .<Integer>map(src -> src.getPowerstats().getIntelligence(), Card::setIntelligence))
                .addMappings(mapper -> mapper.using(stringToInteger)
                        .<Integer>map(src -> src.getPowerstats().getSpeed(), Card::setSpeed))
                .addMappings(mapper -> mapper.using(stringToInteger)
                        .<Integer>map(src -> src.getPowerstats().getDurability(), Card::setDurability))
                .addMappings(mapper -> mapper.using(stringToInteger)
                        .<Integer>map(src -> src.getPowerstats().getPower(), Card::setPower))
                .addMappings(mapper -> mapper.using(stringToInteger)
                        .<Integer>map(src -> src.getPowerstats().getCombat(), Card::setCombat))
        ;

    }

    private Boolean isNull(String property){
        return property == null || property.equals("null");
    }

}
