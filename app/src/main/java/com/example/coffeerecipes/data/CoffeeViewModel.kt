package com.example.coffeerecipes.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeViewModel(application: Application): AndroidViewModel(application) {

    val listAll: LiveData<List<Coffee>>
    private val repository: CoffeeRepository

    init {
        val coffeeDao = CoffeeDatabase.getDatabase(application).coffeeDao()
        repository = CoffeeRepository(coffeeDao)
        listAll = repository.listAll
    }

    fun add(coffee: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(coffee)
        }
    }
}