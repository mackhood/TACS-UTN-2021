package com.example.TACS2021UTN.client.retrofit.superhero;

import com.example.TACS2021UTN.client.retrofit.superhero.entities.SuperHero;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuperHeroService {
    @GET("{id}")
    Call<SuperHero> superHero(@Path("id") Integer id);
}
