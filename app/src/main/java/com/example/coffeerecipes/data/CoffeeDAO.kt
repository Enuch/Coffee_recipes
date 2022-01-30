package com.example.coffeerecipes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoffeeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(coffee: Coffee)

    @Query("SELECT * FROM coffee ORDER BY id ASC")
    fun listAll(): LiveData<List<Coffee>>
}