package com.example.pokelist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokelist.network.Pokemon
import com.example.pokelist.ui.screens.PokeViewModel
import com.example.pokelist.ui.theme.DarkGreen
import com.example.pokelist.ui.theme.Typography
import kotlin.collections.sorted

@Composable
fun PokemonColumn(
    listOfPokemon : List<Pokemon>
) {
    var listOfPokemon by rememberSaveable { mutableStateOf(listOfPokemon) }
    var sortMenuExpanded by remember { mutableStateOf(false) }
    val pokeViewModel: PokeViewModel = viewModel()



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .navigationBarsPadding()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .weight(15f)
        ) {
            items(listOfPokemon) { pokemon -> PokemonCard(pokemon) }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize(),

        ) {


            Button(
                onClick = { pokeViewModel.retry() },
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
                    .padding(6.dp)
                    ,
                shape = RectangleShape,
            ) {
                Text(
                    text = "Load new pokemon",
                    style = Typography.bodyLarge
                )
            }
            Box(Modifier.weight(1f)) {
                IconButton(
                    onClick = { sortMenuExpanded = !sortMenuExpanded },
                    ) {
                    Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Sort pokemon",
                        Modifier.fillMaxSize()
                    )
                }
                DropdownMenu(
                    expanded = sortMenuExpanded,
                    onDismissRequest = { sortMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Name Alphabet",
                                style = Typography.bodySmall
                            )
                        },
                        onClick = {
                            listOfPokemon = pokeViewModel.sortPokemonByNameAlphabet(listOfPokemon)
                            sortMenuExpanded = !sortMenuExpanded
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Moves Alphabet",
                                style = Typography.bodySmall
                            )
                        },
                        onClick = {
                            listOfPokemon = pokeViewModel.sortPokemonByMoveAlphabet(listOfPokemon)
                            sortMenuExpanded = !sortMenuExpanded
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Name Reverse Alphabet",
                                style = Typography.bodySmall
                            )
                        },
                        onClick = {
                            listOfPokemon = pokeViewModel.sortPokemonByNameReverseAlphabet(listOfPokemon)
                            sortMenuExpanded = !sortMenuExpanded
                        }
                    )
                }
            }
        }

    }
}

