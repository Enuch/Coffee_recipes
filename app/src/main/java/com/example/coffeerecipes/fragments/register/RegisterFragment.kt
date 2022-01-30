package com.example.coffeerecipes.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffeerecipes.R
import com.example.coffeerecipes.data.Coffee
import com.example.coffeerecipes.data.CoffeeViewModel
import com.example.coffeerecipes.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var coffeeViewModel: CoffeeViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        coffeeViewModel = ViewModelProvider(this)[CoffeeViewModel::class.java]

        binding.addCoffee.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val type = binding.typeET.text.toString()
        val details = binding.detailsET.text.toString()
        val from = binding.fromET.text.toString()
        val recipe = binding.recipeET.text.toString()
        val priceMin = binding.priceminET.text.toString()
        val priceMax = binding.pricemaxET.text.toString()

        if(inputCheck(type, details, from, recipe, priceMin, priceMax)) {
            val coffee = Coffee(0, type, details, from, recipe, priceMin.toFloat(), priceMax.toFloat())
            coffeeViewModel.add(coffee)
            Toast.makeText(requireContext(), "Sucesso ao cadastrar!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Faltou preencher algum campo!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(type: String, details: String, from: String, recipe: String, priceMin: String, priceMax: String): Boolean {
        return !(type.isEmpty() && details.isEmpty() && from.isEmpty() && recipe.isEmpty() && priceMin.isEmpty() && priceMax.isEmpty())
    }


}