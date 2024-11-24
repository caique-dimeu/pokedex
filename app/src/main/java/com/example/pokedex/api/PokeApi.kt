package com.example.pokedex.api

import com.example.pokedex.models.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonsResponse
}