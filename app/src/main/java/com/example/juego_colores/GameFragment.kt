package com.example.juego_colores

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import kotlin.random.Random
import android.graphics.Color
import androidx.core.content.ContextCompat
import android.os.CountDownTimer
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.juego_colores.viewModel.gviewModel
import kotlin.random.nextInt

class GameFragment : Fragment() {

    private lateinit var gameViewModel: gviewModel

    private var puntaje: Int = 0
    private lateinit var nombreColor: String

    private lateinit var tvTimer: TextView
    private lateinit var countDownTimer: CountDownTimer
    private val totalTime = 30000L

    private lateinit var generatedColorView: View
    private lateinit var generatedColorBtn: MaterialButton

    private lateinit var generatedColorAzul: MaterialButton
    private lateinit var generatedColorRojo: MaterialButton
    private lateinit var generatedColorAmarillo: MaterialButton

    private lateinit var colorList: List<Pair<String, Int>>
    private lateinit var txtpuntaje: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        gameViewModel = ViewModelProvider(requireActivity()).get(gviewModel::class.java)
        generatedColorView = view.findViewById(R.id.generatedColorView)
        generatedColorBtn = view.findViewById(R.id.generatedColorBtn)
        generatedColorAzul = view.findViewById(R.id.azulBtn)
        generatedColorRojo = view.findViewById(R.id.rojoBtn)
        generatedColorAmarillo = view.findViewById(R.id.amarilloBtn)
        tvTimer = view.findViewById(R.id.tempView)
        txtpuntaje = view.findViewById(R.id.puntaje)


        colorList = listOf(
            "celeste" to ContextCompat.getColor(requireContext(), R.color.celeste),
            "azul" to ContextCompat.getColor(requireContext(), R.color.azul),
            "rojo" to ContextCompat.getColor(requireContext(), R.color.rojo),
            "verde" to ContextCompat.getColor(requireContext(), R.color.verde)
        )

            startGame()

        generatedColorBtn.setOnClickListener {
            verificarColor("rojo")
        }
        generatedColorAzul.setOnClickListener {
            verificarColor("verde")
        }
        generatedColorRojo.setOnClickListener {
            verificarColor("azul")
        }
        generatedColorAmarillo.setOnClickListener {
            verificarColor("celeste")
        }


        return view
    }

    private fun generateRandomColor(){
        val (Color, valorColor) = colorList.random()

        nombreColor = Color
        generatedColorView.setBackgroundColor(valorColor)

    }

    private fun startGame() {
        generatedColorBtn.isEnabled = true
        generatedColorAzul.isEnabled = true
        generatedColorRojo.isEnabled = true
        generatedColorAmarillo.isEnabled = true

        generateRandomColor()

        startTimer()
    }

    private fun verificarColor(color:String){

        if (color == nombreColor) {
            puntaje ++
            txtpuntaje.text = "Puntaje: $puntaje"
            generateRandomColor()
        }

    }
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                tvTimer.text = "$seconds s"
            }

            override fun onFinish() {
                tvTimer.text = "¡Se acabó el tiempo!"
                generatedColorBtn.isEnabled = false
                generatedColorAzul.isEnabled = false
                generatedColorRojo.isEnabled = false
                generatedColorAmarillo.isEnabled = false

                // Guardar puntaje
                gameViewModel.agregarPuntaje(puntaje)

                // Navegar a ResultFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResultFragment())
                    .addToBackStack(null)
                    .commit()

            }
        }
        countDownTimer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }


}