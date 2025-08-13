package com.example.pokelist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokelist.network.Pokemon
import com.example.pokelist.ui.components.PokemonColumn
import com.example.pokelist.R
import com.example.pokelist.ui.theme.Typography

@Composable
fun HomeScreen(
    pokeUiState: PokeUiState,

) {
    when (pokeUiState) {
        is PokeUiState.Success -> ResultScreen(pokeUiState.listOfPokemon)
        is PokeUiState.Error -> ErrorScreen()
        is PokeUiState.Loading -> LoadingScreen()
    }
}



@Composable
fun ResultScreen(
    listOfPokemon: List<Pokemon>

) {
    PokemonColumn(listOfPokemon)
}

@Composable
fun ErrorScreen() {
    val pokeViewModel: PokeViewModel = viewModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .padding(24.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.error_icon),
            contentDescription = "Error icon"
        )
        Text(
            text = "Error",
            style = Typography.titleLarge,
            modifier = Modifier
        )
        Text(
            text = "Check your internet connection and try again",
            style = Typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {pokeViewModel.retry()},
        ) {
            Text(
                text = "Try again",
                fontSize = 16.sp

            )
        }
    }

}

@Preview
@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Loading...",
            fontSize = 48.sp,
            modifier = Modifier
            )
    }
}
