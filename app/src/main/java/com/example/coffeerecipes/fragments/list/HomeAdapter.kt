package com.example.coffeerecipes.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeerecipes.R
import com.example.coffeerecipes.model.Coffee

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var coffeeList = emptyList<Coffee>()
    private var coffee = Coffee(1, "", "", "", "", 1F, 1F)

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.coffee_item, parent, false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.MyViewHolder, position: Int) {
        val currentItem = coffeeList[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.idTT).text = currentItem.id.toString()
            findViewById<TextView>(R.id.typeTT).text = currentItem.type
            findViewById<TextView>(R.id.detailsTT).text = currentItem.details

            findViewById<View>(R.id.itemLayout).setOnClickListener {
                val bundle = bundleOf("id" to currentItem.id)
                findNavController().navigate(R.id.action_homeFragment_to_updateFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(coffee: List<Coffee>) {
        this.coffeeList = coffee
        notifyDataSetChanged()
    }
}