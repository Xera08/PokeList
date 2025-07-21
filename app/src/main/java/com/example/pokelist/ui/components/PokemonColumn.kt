package com.example.pokelist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokelist.network.Pokemon
import com.example.pokelist.ui.screens.PokeViewModel
import com.example.pokelist.ui.theme.Typography

@Composable
fun PokemonColumn(
    listOfPokemon : List<Pokemon>
) {
    val pokeViewModel: PokeViewModel = viewModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                pokeViewModel.retry() },
            modifier = Modifier
                .weight(1.5f)
                .width(360.dp)
                .padding(top = 36.dp)
            ,
        ) {
            Text(
                text = "Load new pokemon",
                style = Typography.bodyLarge
                )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .weight(10f)
                .navigationBarsPadding()
        ) {
            items(listOfPokemon) { pokemon -> PokemonCard(pokemon) }
        }

    }
}