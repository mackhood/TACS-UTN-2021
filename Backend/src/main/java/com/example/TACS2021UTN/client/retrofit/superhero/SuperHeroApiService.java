package com.example.TACS2021UTN.client.retrofit.superhero;

import com.example.TACS2021UTN.client.retrofit.superhero.entities.SuperHero;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class SuperHeroApiService {

    private static SuperHeroApiService instance = null;
    //private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://superheroapi.com/api/4309521039059601/";
    private Retrofit retrofit;

    private SuperHeroApiService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SuperHeroApiService instance(){
        if(instance == null)
            instance = new SuperHeroApiService();

        return instance;
    }

    public SuperHero getSuperHero(Integer id) throws IOException {
        SuperHeroService georefService = this.retrofit.create(SuperHeroService.class);
        Call<SuperHero> requestProvinciasArgentinas = georefService.superHero(id);
        Response<SuperHero> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }
}
