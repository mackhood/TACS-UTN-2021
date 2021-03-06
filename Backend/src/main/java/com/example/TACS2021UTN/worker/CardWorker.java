package com.example.TACS2021UTN.worker;

import com.example.TACS2021UTN.client.retrofit.superhero.SuperHeroApiService;
import com.example.TACS2021UTN.client.retrofit.superhero.entities.SuperHero;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CardWorker {

    private ICardRepository cardRepository;
    private Integer callsTillWait = 5; //TODO configurable
    private Integer maxCards = 30; //Por ahora para la entrega 3 tener cartas, voy a buscar solo las 20 primeras cartas
    private Long waitingSeconds = 2L; //TODO Configurable
    private ModelMapper modelMapper;

    public CardWorker(ICardRepository cardRepository, ModelMapper modelMapper){
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
    }

    //TODO worker con quartz que cada X tiempo traiga cartas, o las actualize o algo asi
    public void Execute(){
        SuperHeroApiService service = SuperHeroApiService.instance();
        try{
            for(Integer i = 1; i <= 20; i++){
                if(i % callsTillWait == 0)
                    TimeUnit.SECONDS.sleep(waitingSeconds); //para no sobrecargar la api

                SuperHero hero = service.getSuperHero(i);

                Card newCard = modelMapper.map(hero, Card.class);

                cardRepository.save(newCard);
            }
        }
        catch(Exception ex){

        }
    }



}
