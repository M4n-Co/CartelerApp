package com.example.cartelerapp.home.billboard.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentBillboardBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.home.billboard.adapter.MoviesAdapter
import com.example.cartelerapp.home.billboard.response.Card
import com.example.cartelerapp.home.billboard.response.Entrenamiento
import com.example.cartelerapp.home.billboard.viewModel.BillboardViewModel
import com.example.cartelerapp.home.movieDetail.EntrenamientoInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillboardFragment : Fragment() {

    private var _binding : FragmentBillboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adatador : MoviesAdapter
    private lateinit var fActivity : HomeActivity

    private val viewModel : BillboardViewModel by viewModels()

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
        //initRecycler()
        initListeners()
        viewModelObserves()
    }

    private fun viewModelObserves() {
        viewModel.billboardResult.observe(viewLifecycleOwner){
            if(it != null){
                initRecycler(it.cards)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.pbBillboard.isVisible = it
            binding.rvBillboard.isVisible = !it
        }
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            fActivity.onBackPressed()
        }

        binding.btnSport.setOnClickListener {
            setButtonSelection(true, false, false)
            viewModel.getBillboard("6530106ef252830d42ae9d00")
        }

        binding.btnGym.setOnClickListener {
            setButtonSelection(false, true, false)
            viewModel.getBillboard("6530103cf252830d42ae9cfd")
        }

        binding.btnYoga.setOnClickListener {
            setButtonSelection(false, false, true)
            viewModel.getBillboard("6530197bf252830d42ae9d08")
        }

        binding.btnSport.performClick()
    }

    private fun setButtonSelection(sport: Boolean, gym: Boolean, yoga: Boolean) {
        if (sport){
            binding.btnSport.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondary))
            binding.btnSport.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.btnGym.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnGym.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnYoga.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnYoga.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))
        }
        if (gym){
            binding.btnSport.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnSport.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnGym.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondary))
            binding.btnGym.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.btnYoga.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnYoga.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))
        }
        if (yoga){
            binding.btnSport.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnSport.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnGym.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnGym.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnYoga.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondary))
            binding.btnYoga.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

    }

    private fun initRecycler(cards : List<Card>) {

        adatador = MoviesAdapter(cards) {
            itemClick(it)
        }

        binding.rvBillboard.layoutManager = LinearLayoutManager(requireContext())

        binding.rvBillboard.adapter = adatador
    }

    private fun itemClick(entrenamiento: Entrenamiento){

        val entrena = EntrenamientoInfo(
            entrenamiento._id,
            entrenamiento.descripcion,
            entrenamiento.descripcionCorta,
            entrenamiento.grupoMuscular,
            entrenamiento.id,
            entrenamiento.instrucciones,
            entrenamiento.mediaSource,
            entrenamiento.nivel,
            entrenamiento.nombre,
            entrenamiento.numeroEjercicios,
            entrenamiento.numeroSeries,
            entrenamiento.objetivo
        )

        findNavController().navigate(
            BillboardFragmentDirections.actionBillboardFragmentToMovieDetailFragment(entrena)
        )

    }
}