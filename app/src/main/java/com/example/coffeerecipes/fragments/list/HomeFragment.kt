package com.example.coffeerecipes.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.coffeerecipes.R
import com.example.coffeerecipes.viewModel.HomeViewModel
import com.example.coffeerecipes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val adapter = HomeAdapter()
        val recycleView = binding.recycleview
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.listAll.observe(viewLifecycleOwner, Observer { coffee ->
            adapter.setData(coffee)
        })

        binding.addBT.setOnClickListener {
            findNavController(it).navigate(R.id.action_homeFragment_to_registerFragment)
        }

        setHasOptionsMenu(true)

        recycleView.addOnItemTouchListener(HomeTouchListener(requireContext(), recycleView,
            object : HomeTouchListener.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    val bundle = bundleOf("id" to adapter.coffeeList[position].id)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
                }

                override fun onItemLongClick(v: View, position: Int) {
                    val bundle = bundleOf("id" to adapter.coffeeList[position].id)
                    findNavController().navigate(R.id.action_homeFragment_to_updateFragment, bundle)
                }
            }),)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _->
            homeViewModel.deleteAll()
            Toast.makeText(requireContext(), "Sucesso ao deletar todos!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete todos")
        builder.setMessage("Tem certeza que deseja deletar tudo?")
        builder.create().show()
    }

}