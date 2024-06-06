package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.Screens
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.LoginViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Menu(navController: NavController,viewModelLogin: LoginViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu pesca") },
                navigationIcon = {

                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Text(text = "Usuario: ${viewModelLogin.username}")
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {navController.navigate(route = Screens.ListadoPesca.route)}) {
                Text(text = "Lista pesca")
            }
            Button(onClick = {navController.navigate(route = Screens.TestDelPescador.route)}) {
                Text(text = "Test del Pescador")
            }
        }
    }
}