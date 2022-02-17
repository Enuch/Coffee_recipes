package com.example.coffeerecipes.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.coffeerecipes.R
import com.example.coffeerecipes.databinding.FragmentDetailBinding
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.viewModel.DetailViewModel
import com.example.coffeerecipes.viewModel.HomeViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    lateinit var coffee: LiveData<Coffee>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val id = arguments?.getInt("id")
        coffee = detailViewModel.getCoffee(id!!)

        coffee.observe(viewLifecycleOwner) { coffee ->
            binding.typeVW.text = coffee.type
            binding.detailVW.text = coffee.details
            binding.fromVW.text = coffee.from
            binding.recipeVW.text = coffee.recipe
            binding.priceminVW.text = coffee.priceMin.toString()
            binding.pricemaxVW.text = coffee.priceMax.toString()
        }


        return binding.root
    }

}