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

    // deprecated function used for testing the retrieving pokemon data
    fun getSinglePokemon() {
        viewModelScope.launch {
            pokeUiState = try {
                val pokemon: List<Pokemon> = listOf(PokeApi.retrofitService.getPokemon(5))
                PokeUiState.Success(pokemon)
            } catch (e: IOException) {
                PokeUiState.Error
            }

        }
    }

    fun getListOfPokemon() {
        viewModelScope.launch {
            pokeUiState = try {
                val listOfPokemon: List<Pokemon> =
                    (1..10).map { PokeApi.retrofitService.getPokemon((0..500).random()) }
                PokeUiState.Success(listOfPokemon)
            } catch (e: IOException) {
                PokeUiState.Error
            }
        }
    }

    fun retry() {
        pokeUiState = PokeUiState.Loading
        getListOfPokemon()
    }
}