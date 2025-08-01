package com.example.pokelist.ui

import androidx.compose.runtime.Composable
import com.example.pokelist.ui.screens.HomeScreen
import com.example.pokelist.ui.screens.PokeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PokeListApp() {
    val pokeViewModel: PokeViewModel = viewModel()
    HomeScreen(pokeUiState = pokeViewModel.pokeUiState)
}