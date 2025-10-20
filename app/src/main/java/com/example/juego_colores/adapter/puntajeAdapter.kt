package com.example.juego_colores.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.juego_colores.R

class PuntajeAdapter(
    private var puntajes: List<Int>
) : RecyclerView.Adapter<PuntajeAdapter.PuntajeViewHolder>() {

    inner class PuntajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPuntaje: TextView = itemView.findViewById(R.id.tvPuntaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntajeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puntaje, parent, false)
        return PuntajeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuntajeViewHolder, position: Int) {
        val puntaje = puntajes[position]
        holder.tvPuntaje.text = "Puntaje: $puntaje"
    }

    override fun getItemCount(): Int = puntajes.size

    // Método para actualizar la lista
    fun actualizarLista(nuevaLista: List<Int>) {
        this.puntajes = nuevaLista
        notifyDataSetChanged()
    }
}
