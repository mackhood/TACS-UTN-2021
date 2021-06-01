package com.example.TACS2021UTN;

import com.example.TACS2021UTN.client.retrofit.superhero.SuperHeroApiService;
import com.example.TACS2021UTN.client.retrofit.superhero.entities.SuperHero;
import com.example.TACS2021UTN.worker.CardWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Tacs2021UtnApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Tacs2021UtnApplication.class, args);
	}



}
