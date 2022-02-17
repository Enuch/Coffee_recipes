package com.example.coffeerecipes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.coffeerecipes.data.CoffeeDatabase
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    val listAll: LiveData<List<Coffee>>
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
        listAll = repository.listAll
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}