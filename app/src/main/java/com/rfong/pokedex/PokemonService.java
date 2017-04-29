package com.rfong.pokedex;

import com.rfong.pokedex.models.Pokemon;
import com.rfong.pokedex.models.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rfong on 4/28/17.
 */

public interface PokemonService {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

    @GET("pokemon/")
    Call<PokemonList> getPokemon();

}
