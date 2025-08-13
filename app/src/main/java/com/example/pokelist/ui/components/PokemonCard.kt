package com.example.pokelist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.pokelist.network.Pokemon
import com.example.pokelist.ui.theme.DarkCreme
import com.example.pokelist.ui.theme.LightCreme
import com.example.pokelist.ui.theme.MainGradient
import com.example.pokelist.ui.theme.PokeListTheme
import com.example.pokelist.ui.theme.Typography

@Composable
fun PokemonCard(
    pokemon: Pokemon
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .shadow(
                clip = true,
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = MainGradient
                    )
                )
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = pokemon.sprites.frontDefault,
                contentDescription = "Pokemon front sprite",
                modifier = Modifier
                    .size(128.dp)
                    .padding(4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    )
            )
        }
        Text(
            text = "#${pokemon.id}\n${pokemon.name}",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
        Text(
            text = "Moves: ",
            modifier = Modifier
                .padding(16.dp),
            fontSize = 18.sp
        )
        Text(
            text = pokemon.moves[0].move.name,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = MainGradient
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp),
        )
        Text(
            text = pokemon.moves[1].move.name,
            color = Color.White,
            modifier = Modifier
                .padding(top = 4.dp, start = 16.dp, bottom = 16.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = MainGradient
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                .padding(8.dp)
        )

    }


}


