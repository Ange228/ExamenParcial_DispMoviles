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
import kotlin.random.nextInt

class GameFragment : Fragment() {
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
        tvTimer = view.findViewById(R.id.tempView)

        colorList = listOf(
            "celeste" to ContextCompat.getColor(requireContext(), R.color.celeste),
            "azul" to ContextCompat.getColor(requireContext(), R.color.azul),
            "rojo" to ContextCompat.getColor(requireContext(), R.color.rojo),
            "verde" to ContextCompat.getColor(requireContext(), R.color.verde)
        )

        generatedColorBtn.isEnabled = false
        generatedColorAzul.isEnabled = false
        generatedColorRojo.isEnabled = false
        generatedColorAmarillo.isEnabled = false
            startGame()

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

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                tvTimer.text = "$seconds s"
            }

            override fun onFinish() {
                tvTimer.text = "¡Se acabó!"
                generatedColorBtn.isEnabled = false
                generatedColorAzul.isEnabled = false
                generatedColorRojo.isEnabled = false
                generatedColorAmarillo.isEnabled = false

            }
        }
        countDownTimer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }


}