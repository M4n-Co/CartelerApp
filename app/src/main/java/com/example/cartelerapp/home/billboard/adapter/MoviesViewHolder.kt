package com.example.cartelerapp.home.billboard.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cartelerapp.databinding.ItemMovieBinding

class MoviesViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movie: String, onClick: () -> Unit) {

        binding.tvMovieTitle.text = movie
        binding.tvMovieTitle.isSelected = true

        itemView.setOnClickListener{onClick()}
    }

}