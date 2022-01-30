package com.example.coffeerecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coffeerecipes.model.Coffee

@Dao
interface CoffeeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(coffee: Coffee)

    @Update
    suspend fun update(coffee: Coffee)

    @Delete
    suspend fun delete(coffee: Coffee)

    @Query("DELETE FROM coffee")
    suspend fun deleteAll()

    @Query("SELECT * FROM coffee ORDER BY id ASC")
    fun listAll(): LiveData<List<Coffee>>

    @Query("SELECT * FROM coffee WHERE id = :id")
    fun findById(id: Int): LiveData<Coffee>

}