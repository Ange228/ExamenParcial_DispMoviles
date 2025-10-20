package com.example.juego_colores

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import kotlin.random.Random
import android.graphics.Color
import kotlin.random.nextInt

class GameFragment : Fragment() {
    private lateinit var generatedColorView: View
    private lateinit var generatedColorBtn: MaterialButton

    private lateinit var generatedColorAzul: MaterialButton
    private lateinit var generatedColorRojo: MaterialButton
    private lateinit var generatedColorAmarillo: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        generatedColorView = view.findViewById(R.id.generatedColorView)
        generatedColorBtn = view.findViewById(R.id.generatedColorBtn)
        generatedColorAzul = view.findViewById(R.id.azulBtn)
        generatedColorRojo = view.findViewById(R.id.rojoBtn)
        generatedColorAmarillo = view.findViewById(R.id.amarilloBtn)

        generatedColorBtn.setOnClickListener {
            generateRandomColor()

        }
        return view
    }

    private fun generateRandomColor(){
        val color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

        generatedColorView.setBackgroundColor(color)

    }

}