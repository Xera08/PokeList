package com.example.pokelist.network

interface PokemonRepository {
    suspend fun getPokemon(): Pokemon
}