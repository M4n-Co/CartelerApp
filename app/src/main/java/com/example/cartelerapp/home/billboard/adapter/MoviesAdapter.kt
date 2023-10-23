package com.example.cartelerapp.home.billboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cartelerapp.R
import com.example.cartelerapp.home.billboard.response.Card
import com.example.cartelerapp.home.billboard.response.Entrenamiento

class MoviesAdapter(private val list: List<Card>, private var onClick :(Entrenamiento) -> Unit):RecyclerView.Adapter<MoviesViewHolder>() {

    var context : Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return MoviesViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val card = list[position]
        context?.let {
            holder.bind(card, it, onClick)
        }
    }
}