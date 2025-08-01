package com.example.pokelist.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val moves: List<MoveWrapper>,
    val sprites: Sprites
    )

@Serializable
data class MoveWrapper(
    val move: Move
)

@Serializable
data class Move(
    val name: String
)


@Serializable
data class Sprites(
    @SerialName("front_default")
    val frontDefault: String
)

