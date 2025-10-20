package com.example.juego_colores.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class gviewModel: ViewModel() {
        private val _puntajes = MutableLiveData<MutableList<Int>>(mutableListOf())
        val puntajes: LiveData<MutableList<Int>> get() = _puntajes

        fun agregarPuntaje(p: Int) {
            _puntajes.value?.add(p)
            _puntajes.value = _puntajes.value
        }
    }
