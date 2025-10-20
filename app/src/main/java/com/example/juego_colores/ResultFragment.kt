package com.example.juego_colores


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.juego_colores.adapter.puntajeAdapter
import com.example.juego_colores.viewModel.gviewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: puntajeAdapter
    private lateinit var gameViewModel: gviewModel

    private lateinit var atrasView: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvPuntajes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        gameViewModel = ViewModelProvider(requireActivity()).get(gviewModel::class.java)
        adapter = puntajeAdapter(gameViewModel.puntajes.value ?: emptyList())
        recyclerView.adapter = adapter
        atrasView = view.findViewById(R.id.atrasView)

        // Observar cambios en los puntajes
        gameViewModel.puntajes.observe(viewLifecycleOwner) { newList ->
            adapter.actualizarLista(newList)
        }

        atrasView.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // opcional, para cerrar ResultFragment y no volver al back stack
        }

    }
}