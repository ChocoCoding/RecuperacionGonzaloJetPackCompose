package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities

import androidx.compose.runtime.mutableStateListOf

data class Preguntas(val text:String,val opciones:List<String>,val respuestaCorrecta:String) {

}

fun getListaPreguntas():List<Preguntas>{
    val  listaPreguntas : MutableList<Preguntas> = mutableStateListOf(
        Preguntas("¿Que tipo de peces son mas propensos a contener Mercurio?", listOf("Peces Blancos","Peces Azules","Peces de Gran Tamaño"),"Peces de Gran Tamaño"),
        Preguntas("¿De que familia son los Chocos?", listOf("Molusco Cefalópodo","Cefalópodo","Animales de Compañia"),"Molusco Cefalópodo"),
        Preguntas("¿Comúnmente, como se llama el artilugio con el que se pescan Chocos?", listOf("Anzuelo Choquil","Potera","Anzuelo de 3 puntas"),"Potera")
    )
    return listaPreguntas;
}