package com.example.coffeerecipes.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coffeerecipes.R
import com.example.coffeerecipes.databinding.FragmentDetailBinding
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.viewModel.CoffeeViewModel

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private lateinit var coffeeViewModel: CoffeeViewModel
    lateinit var coffee: LiveData<Coffee>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        coffeeViewModel = ViewModelProvider(this)[CoffeeViewModel::class.java]

        var id = arguments?.getInt("id")
        coffee = coffeeViewModel.getCoffee(id!!)

        coffee.observe(viewLifecycleOwner, Observer { coffee ->
            binding.typeVW.setText(coffee.type)
            binding.detailVW.setText(coffee.details)
            binding.fromVW.setText(coffee.from)
            binding.recipeVW.setText(coffee.recipe)
            binding.priceminVW.setText(coffee.priceMin.toString())
            binding.pricemaxVW.setText(coffee.priceMax.toString())
        })


        return binding.root
    }

}