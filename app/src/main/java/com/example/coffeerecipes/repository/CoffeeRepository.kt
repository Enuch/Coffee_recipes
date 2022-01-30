package com.example.coffeerecipes.repository

import androidx.lifecycle.LiveData
import com.example.coffeerecipes.data.CoffeeDAO
import com.example.coffeerecipes.model.Coffee

class CoffeeRepository(private val coffeeDAO: CoffeeDAO) {
    val listAll: LiveData<List<Coffee>> = coffeeDAO.listAll()

    suspend fun add(coffee: Coffee) {
        coffeeDAO.add(coffee)
    }

    suspend fun update(coffee: Coffee) {
        coffeeDAO.update(coffee)
    }

    suspend fun delete(coffee: Coffee) {
        coffeeDAO.delete(coffee)
    }

    suspend fun deleteAll() {
        coffeeDAO.deleteAll()
    }

    fun findById(id: Int): LiveData<Coffee> {
        return coffeeDAO.findById(id)
    }
}