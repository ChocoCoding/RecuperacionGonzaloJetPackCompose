package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.ArticulosPesca
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.getListaArticulosPesca
class ListadoPescaViewModel: ViewModel() {

        var articulosSeleccionados = mutableStateOf(listOf<ArticulosPesca>());
        var contadorArticulos = mutableIntStateOf(0);
        var seleccionarTodos = mutableStateOf(false)
        var showDialog =  mutableStateOf(false)

        fun confirmarCompra() {
            showDialog.value = true
        }

        fun onArticuloSeleccionadoChange(articulo: ArticulosPesca, seleccionado: Boolean){
            val articuloSeleccionadoActual = articulosSeleccionados.value.toMutableList();
            if (seleccionado){
                articuloSeleccionadoActual.add(articulo)
                contadorArticulos.value++
            }else{
                articuloSeleccionadoActual.remove(articulo)
                contadorArticulos.value--
            }
            articulosSeleccionados.value = articuloSeleccionadoActual
        }

        fun eliminarArticulo(articulo: ArticulosPesca){
            val articuloSeleccionadoActual = articulosSeleccionados.value.toMutableList()
            if (articuloSeleccionadoActual.contains(articulo)){
                articuloSeleccionadoActual.remove(articulo)
                contadorArticulos.value--
                articulosSeleccionados.value= articuloSeleccionadoActual
            }
        }

        fun seleccionarTodos(seleccionar:Boolean){
            articulosSeleccionados.value = if (seleccionar){
                getListaArticulosPesca().toList()
            }else{
                emptyList()
            }
            contadorArticulos.value = if (seleccionar){
                getListaArticulosPesca().size
            }else{
                0
            }
        }

        fun resetearArticulosSeleccionados() {
            articulosSeleccionados.value = emptyList()
            contadorArticulos.value = 0
        }



}