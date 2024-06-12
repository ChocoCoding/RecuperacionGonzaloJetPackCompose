package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities

import androidx.compose.runtime.mutableStateListOf

data class User(val username: String,val password: String) {

}

fun getListaUsuarios():List<User>{
    val  listaUsuarios : MutableList<User> = mutableStateListOf(
        User("gonzalo@gmail.com", "abc123."),
        User("gonzalo@teis.com", "abc123."),
        User("gonzalo@teis.gal", "abc123.")

    )
    return listaUsuarios;
}