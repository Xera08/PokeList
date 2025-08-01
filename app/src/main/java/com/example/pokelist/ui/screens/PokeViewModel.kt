package com.example.pokelist.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokelist.network.PokeApi
import com.example.pokelist.network.Pokemon
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PokeUiState {
    data class Success(val listOfPokemon: List<Pokemon>): PokeUiState
    object Error: PokeUiState
    object Loading: PokeUiState
}

class PokeViewModel: ViewModel() {
    var pokeUiState: PokeUiState by mutableStateOf(PokeUiState.Loading)
        private set

    init {
        getListOfPokemon()
    }

    fun getListOfPokemon() {
        viewModelScope.launch {
            pokeUiState = try {
                val setOfPokemon: MutableSet<Pokemon> = mutableSetOf()
                while (setOfPokemon.size < 10) {
                    setOfPokemon.add(PokeApi.retrofitService.getPokemon((0..500).random()))
                }

                PokeUiState.Success(setOfPokemon.toList())
            } catch (e: IOException) {
                PokeUiState.Error
            }
        }
    }

    fun sortPokemonByNameAlphabet(listOfPokemon: List<Pokemon>): List<Pokemon> {
        return (listOfPokemon.sortedBy { it.name } )
    }

    fun sortPokemonByMoveAlphabet(listOfPokemon: List<Pokemon>): List<Pokemon> {
        return (listOfPokemon.sortedBy { it.moves[0].move.name })
    }


    fun sortPokemonByNameReverseAlphabet(listOfPokemon: List<Pokemon>): List<Pokemon> {
        return (listOfPokemon.sortedBy { it.name } ).reversed()
    }

    fun retry() {
        pokeUiState = PokeUiState.Loading
        getListOfPokemon()
    }

}