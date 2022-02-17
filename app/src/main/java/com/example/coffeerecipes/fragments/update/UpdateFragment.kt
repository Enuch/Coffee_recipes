package com.example.coffeerecipes.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffeerecipes.R
import com.example.coffeerecipes.databinding.FragmentUpdateBinding
import com.example.coffeerecipes.model.Coffee
import com.example.coffeerecipes.viewModel.HomeViewModel
import com.example.coffeerecipes.viewModel.UpdateViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var updateViewModel: UpdateViewModel
    lateinit var coffee: LiveData<Coffee>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)

        updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]


        val id = arguments?.getInt("id")
        coffee = updateViewModel.getCoffee(id!!)

        coffee.observe(viewLifecycleOwner) { coffee ->
            binding.uptypeET.setText(coffee.type)
            binding.updetailsET.setText(coffee.details)
            binding.upfromET.setText(coffee.from)
            binding.uprecipeET.setText(coffee.recipe)
            binding.uppriceminET.setText(coffee.priceMin.toString())
            binding.uppricemaxET.setText(coffee.priceMax.toString())
        }

        binding.upCoffee.setOnClickListener {
            updateDataToDatabase()
        }

        setHasOptionsMenu(true)

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
            val coffee = Coffee(requireArguments().getInt("id"), type, details, from, recipe, priceMin.toFloat(), priceMax.toFloat())
            updateViewModel.update(coffee)
            Toast.makeText(requireContext(), "Sucesso ao atualizar!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Faltou preencher algum campo!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(type: String, details: String, from: String, recipe: String, priceMin: String, priceMax: String): Boolean {
        return !(type.isEmpty() && details.isEmpty() && from.isEmpty() && recipe.isEmpty() && priceMin.isEmpty() && priceMax.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            delete()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun delete() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _->
            val type = binding.uptypeET.text.toString()
            val details = binding.updetailsET.text.toString()
            val from = binding.upfromET.text.toString()
            val recipe = binding.uprecipeET.text.toString()
            val priceMin = binding.uppriceminET.text.toString()
            val priceMax = binding.uppricemaxET.text.toString()
            val coffee = Coffee(requireArguments().getInt("id"), type, details, from, recipe, priceMin.toFloat(), priceMax.toFloat())
            updateViewModel.delete(coffee)
            Toast.makeText(requireContext(), "Sucesso ao deletar!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${arguments?.getString("type")}")
        builder.setMessage("Tem certeza que deseja deletar ${arguments?.getString("type")}?")
        builder.create().show()
    }
}