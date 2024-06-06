package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities

import androidx.compose.runtime.mutableStateListOf
import com.example.recuperacion_gonzalo_campos_jetpackcompose.R

data class ArticulosPesca(val nombre: String, val precio: Double, val imagenResId: Int) {
}

fun getListaArticulosPesca():List<ArticulosPesca> {
    val lista : MutableList<ArticulosPesca> = mutableStateListOf(
        ArticulosPesca("Daiwa Legalis Seabass",54.90, R.drawable.c1),
        ArticulosPesca("Vercelli Oxygen Sportiva",54.90, R.drawable.c2),
        ArticulosPesca("Mochila Daiwa Surf 50L",127.95,R.drawable.m1),
        ArticulosPesca("Duel Finplus Tip Run Rattle",19.95,R.drawable.s1),
        ArticulosPesca("Daiwa Exceler 20 LT",59.95,R.drawable.ca),
        ArticulosPesca("Equipo Vercelli Capitano",33.75,R.drawable.e1)
        )
    return lista
}
