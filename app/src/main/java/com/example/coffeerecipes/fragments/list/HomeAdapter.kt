package com.example.coffeerecipes.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeerecipes.R
import com.example.coffeerecipes.data.Coffee

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var coffeeList = emptyList<Coffee>()

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