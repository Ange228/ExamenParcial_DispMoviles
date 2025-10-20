package com.example.juego_colores
import android.view.animation.Interpolator

class MyBounceInterpolator (amplitude: Double, frequency: Double) :
        Interpolator {
            private var mAmplitude = 1.0
            private var mFrecuency = 10.0
    override fun getInterpolation(input: Float): Float {
        val time = input
        return (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrecuency * time) + 1).toFloat()
    }
    init {
        mAmplitude = amplitude
        mFrecuency = frequency
    }
        }