package com.example.coffeerecipes.data

import androidx.lifecycle.LiveData

class CoffeeRepository(private val coffeeDAO: CoffeeDAO) {
    val listAll: LiveData<List<Coffee>> = coffeeDAO.listAll()

    suspend fun add(coffee: Coffee) {
        coffeeDAO.add(coffee)
    }
}