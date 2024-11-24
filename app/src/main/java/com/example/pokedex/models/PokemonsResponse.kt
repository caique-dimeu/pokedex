package com.example.pokedex.models

data class PokemonsResponse(
    val count: Int,
    val results: List<Pokemon>,
    val next: String,
)