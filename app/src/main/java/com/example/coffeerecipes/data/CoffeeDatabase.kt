package com.example.coffeerecipes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffeerecipes.model.Coffee

@Database(entities = [Coffee::class], version = 1, exportSchema = false)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDAO
}