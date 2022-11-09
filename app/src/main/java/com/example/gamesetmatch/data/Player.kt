package com.example.gamesetmatch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val isFootball: Boolean,
    val isBasketball: Boolean,
    val footballGrade: Int,
    val basketballGrade: Int
)