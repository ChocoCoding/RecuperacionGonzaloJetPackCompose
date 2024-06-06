package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.login.AppLogin
import com.example.examenpmdmterceraevaluacion.ui.theme.screens.login.AppRegistrar
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.lista_pesca.App
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.lista_pesca.AppCarrito
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.LoginViewModel
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.menu.Menu
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.Screens
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.test_pescador.AppTestPescador
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.ListadoPescaViewModel
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.TestDelPescadorViewModel


@Composable
fun Navigation(){
    val navController = rememberNavController()
    val loginViewModel = viewModel<LoginViewModel>()
    val listadoPescaViewModel = viewModel<ListadoPescaViewModel>()
    val testDelPescadorViewModel = viewModel<TestDelPescadorViewModel>()

    NavHost(navController, startDestination = Screens.Login.route){
        composable(route= Screens.Login.route){
            AppLogin(navController = navController, viewModel = loginViewModel)
        }
        composable(route=Screens.Menu.route){
            Menu(navController = navController, viewModelLogin = loginViewModel)
        }
        composable(route = Screens.Registrar.route){
            AppRegistrar(navController = navController, viewModel = loginViewModel)
        }

        composable(route = Screens.ListadoPesca.route){
          App(navController = navController, viewModelListadoPesca = listadoPescaViewModel)
        }

        composable(route = Screens.Carrito.route){
            AppCarrito(navController = navController, viewModelListadoPesca = listadoPescaViewModel)
        }

        composable(route = Screens.TestDelPescador.route){
            AppTestPescador(navController = navController, testDelPescadorViewModel = testDelPescadorViewModel)
        }

    }


}