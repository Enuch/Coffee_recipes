package com.example.coffeerecipes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee")
data class Coffee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val details: String,
    val from: String,
    val recipe: String,
    val priceMin: Float,
    val priceMax: Float
)