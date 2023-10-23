package com.example.cartelerapp.home.billboard.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cartelerapp.databinding.ItemMovieBinding
import com.example.cartelerapp.home.billboard.response.Card
import com.example.cartelerapp.home.billboard.response.Entrenamiento

class MoviesViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(card: Card, context: Context, onClick: (Entrenamiento) -> Unit) {

        binding.tvMovieTitle.text = card.titulo
        binding.tvMovieTitle.isSelected = true

        Glide.with(context)
            .load(card.imagen.url)
            //.placeholder(R.drawable.ic_flag_placeholder)
            .into(binding.ivFrontPage)

        itemView.setOnClickListener{onClick(card.entrenamiento)}
    }

}