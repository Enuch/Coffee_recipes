package com.example.coffeerecipes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coffeerecipes.data.CoffeeDAO
import com.example.coffeerecipes.data.CoffeeDatabase
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeViewModel(application: Application): AndroidViewModel(application) {

    val listAll: LiveData<List<Coffee>>
    var coffee: LiveData<Coffee>
    private val repository: CoffeeRepository

    init {
        val coffeeDao = CoffeeDatabase.getDatabase(application).coffeeDao()
        repository = CoffeeRepository(coffeeDao)
        listAll = repository.listAll
        coffee = MutableLiveData()
    }

    fun add(coffee: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(coffee)
        }
    }

    fun update(coffee: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(coffee)
        }
    }

    fun getCoffee(id: Int): LiveData<Coffee> {
        coffee = repository.findById(id)
        return this.coffee
    }
}