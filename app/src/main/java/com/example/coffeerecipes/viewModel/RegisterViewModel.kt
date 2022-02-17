package com.example.coffeerecipes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.coffeerecipes.data.CoffeeDatabase
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    private val repository: CoffeeRepository

    private val db:CoffeeDatabase by lazy {
        Room.databaseBuilder(
            application.applicationContext,
            CoffeeDatabase::class.java,
            "coffee.sqlite"
        ).build()
    }

    init {
        val coffeeDao = db.coffeeDao()
        repository = CoffeeRepository(coffeeDao)
    }

    fun add(coffee: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(coffee)
        }
    }
}