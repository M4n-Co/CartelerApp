package com.example.cartelerapp.home.billboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cartelerapp.R

class MoviesAdapter(private val list: List<String>, private var onClick :() -> Unit):RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MoviesViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie, onClick)
    }
}