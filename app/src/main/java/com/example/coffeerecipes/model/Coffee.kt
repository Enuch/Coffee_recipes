package com.example.coffeerecipes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable