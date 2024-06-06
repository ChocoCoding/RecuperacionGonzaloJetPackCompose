package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens


sealed class Screens(val route: String) {

    object Login: Screens("initial_screen")
    object Registrar: Screens("registrar")
    object Menu:Screens("Menu")
    object ListadoPesca: Screens("listado_pesca")
    object Carrito: Screens("carrito")
    object TestDelPescador: Screens("test_pescador")

}