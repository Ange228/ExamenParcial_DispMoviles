package com.example.juego_colores


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.juego_colores.adapter.puntajeAdapter
import com.example.juego_colores.viewModel.gviewModel


class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: puntajeAdapter
    private lateinit var gameViewModel: gviewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvPuntajes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        gameViewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        adapter = PuntajeAdapter(gameViewModel.puntajes.value ?: emptyList())
        recyclerView.adapter = adapter

        // Observar cambios en los puntajes
        gameViewModel.puntajes.observe(viewLifecycleOwner) { newList ->
            adapter.actualizarLista(newList)
        }

    }
}