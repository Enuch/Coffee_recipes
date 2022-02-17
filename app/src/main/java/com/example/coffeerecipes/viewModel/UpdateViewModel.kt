package com.example.coffeerecipes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.coffeerecipes.data.CoffeeDatabase
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application): AndroidViewModel(application) {

    var coffee: LiveData<Coffee>
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
        coffee = MutableLiveData()
    }

    fun update(coffee: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(coffee)
        }
    }

    fun delete(coffee: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(coffee)
        }
    }

    fun getCoffee(id: Int): LiveData<Coffee> {
        coffee = repository.findById(id)
        return this.coffee
    }
}