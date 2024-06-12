package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.Preguntas
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.User
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.getListaPreguntas
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.getListaUsuarios
import java.io.File

class TestDelPescadorViewModel : ViewModel(){
    private var indicePreguntaActual by mutableStateOf(0)

    var preguntaActual by mutableStateOf(getListaPreguntas()[indicePreguntaActual])

    var mostrarToast by mutableStateOf(false)

    var aciertos by mutableStateOf(0)

    fun enviarRespuesta(repuestaSeleccionada: String){
        if (repuestaSeleccionada == preguntaActual.respuestaCorrecta){
            showSiguientePregunta()
            aciertos++
        }else{
            mostrarToast = true
            showSiguientePregunta()
        }
    }

    fun showSiguientePregunta(){
        indicePreguntaActual++ //1
        if (indicePreguntaActual < getListaPreguntas().size){
            preguntaActual = getListaPreguntas()[indicePreguntaActual]
        }else preguntaActual = Preguntas("No hay más preguntas.", emptyList(), "")
    }


    fun resetTest() {
        indicePreguntaActual = 0
        preguntaActual = getListaPreguntas()[indicePreguntaActual]
        aciertos = 0
    }
}