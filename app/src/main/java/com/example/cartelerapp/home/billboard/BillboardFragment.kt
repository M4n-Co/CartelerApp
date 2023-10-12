package com.example.cartelerapp.home.billboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentBillboardBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.home.billboard.adapter.MoviesAdapter


class BillboardFragment : Fragment() {

    private var _binding : FragmentBillboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adatador : MoviesAdapter
    private lateinit var fActivity : HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBillboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fActivity = (activity as? HomeActivity)!!
        initUI()
    }

    private fun initUI() {
        initRecycler()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            fActivity.onBackPressed()
        }
    }

    private fun initRecycler() {

        val peliculas = listOf(
            "El Padrino",
            "Pulp Fiction",
            "El caballero oscuro",
            "La lista de Schindler",
            "El señor de los anillos: el retorno del rey",
            "Forrest Gump",
            "Matrix",
            "Gladiador",
            "El club de la lucha",
            "Braveheart",
            "Reservoir Dogs",
            "El silencio de los corderos",
            "Salvar al soldado Ryan",
            "La vida es bella",
            "El sexto sentido",
            "Memento",
            "American History X",
            "El gran Lebowski",
            "Los siete samuráis",
            "Psicosis",
            "Cadena perpetua",
            "Casablanca",
            "Ciudadano Kane",
            "2001: Una odisea del espacio",
            "Blade Runner"
        )

        adatador = MoviesAdapter(peliculas) { itemClick() }

        val grid = GridLayoutManager(context,3)
        binding.rvBillboard.layoutManager = grid

        binding.rvBillboard.adapter = adatador
    }

    private fun itemClick(){

        findNavController().navigate(R.id.action_billboardFragment_to_movieDetailFragment)

    }
}