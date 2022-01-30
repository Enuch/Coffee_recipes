package com.example.coffeerecipes.fragments.update

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffeerecipes.R
import com.example.coffeerecipes.databinding.FragmentUpdateBinding
import com.example.coffeerecipes.fragments.list.HomeAdapter
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.viewModel.CoffeeViewModel

class UpdateFragment : Fragment() {

    lateinit var binding: FragmentUpdateBinding
    private lateinit var coffeeViewModel: CoffeeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)

        coffeeViewModel = ViewModelProvider(this)[CoffeeViewModel::class.java]


        var id = arguments?.getInt("id")
        var coffee = coffeeViewModel.getCoffee(id!!)

        coffee.observe(viewLifecycleOwner, Observer { coffee ->
            binding.uptypeET.setText(coffee.type)
            binding.updetailsET.setText(coffee.details)
            binding.upfromET.setText(coffee.from)
            binding.uprecipeET.setText(coffee.recipe)
            binding.uppriceminET.setText(coffee.priceMin.toString())
            binding.uppricemaxET.setText(coffee.priceMax.toString())
        })

        binding.upCoffee.setOnClickListener {
            updateDataToDatabase()
        }

        return binding.root
    }

    private fun updateDataToDatabase() {
        val type = binding.uptypeET.text.toString()
        val details = binding.updetailsET.text.toString()
        val from = binding.upfromET.text.toString()
        val recipe = binding.uprecipeET.text.toString()
        val priceMin = binding.uppriceminET.text.toString()
        val priceMax = binding.uppricemaxET.text.toString()

        if(inputCheck(type, details, from, recipe, priceMin, priceMax)) {
            val coffee = Coffee(arguments!!.getInt("id"), type, details, from, recipe, priceMin.toFloat(), priceMax.toFloat())
            coffeeViewModel.update(coffee)
            Toast.makeText(requireContext(), "Sucesso ao atualizar!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Faltou preencher algum campo!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(type: String, details: String, from: String, recipe: String, priceMin: String, priceMax: String): Boolean {
        return !(type.isEmpty() && details.isEmpty() && from.isEmpty() && recipe.isEmpty() && priceMin.isEmpty() && priceMax.isEmpty())
    }

}