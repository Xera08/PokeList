package com.example.pokelist.network

class PokemonRepositoryImp() : PokemonRepository {
    override suspend fun getPokemon(): Pokemon {
        return PokeApi.retrofitService.getPokemon((0..500).random())
    }
}