package com.example.coffeerecipes.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.coffeerecipes.R
import com.example.coffeerecipes.viewModel.CoffeeViewModel
import com.example.coffeerecipes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var coffeeViewModel: CoffeeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val adapter = HomeAdapter()
        val recycleView = binding.recycleview
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        coffeeViewModel = ViewModelProvider(this)[CoffeeViewModel::class.java]
        coffeeViewModel.listAll.observe(viewLifecycleOwner, Observer { coffee ->
            adapter.setData(coffee)
        })

        binding.addBT.setOnClickListener {
            findNavController(it).navigate(R.id.action_homeFragment_to_registerFragment)
        }

        return binding.root
    }

}