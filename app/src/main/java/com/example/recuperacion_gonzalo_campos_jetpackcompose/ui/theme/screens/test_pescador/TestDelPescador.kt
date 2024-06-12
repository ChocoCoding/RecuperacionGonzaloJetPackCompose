package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.test_pescador

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.lista_pesca.CarritoCompraIcono
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.TestDelPescadorViewModel




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppTestPescador(navController: NavController,testDelPescadorViewModel: TestDelPescadorViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Test del Pescador") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {}
            )
        }
    ) {
        //val preguntaActual = testDelPescadorViewModel.preguntaActual

        Column(modifier = Modifier.padding(16.dp),
            ) {
            if (testDelPescadorViewModel.preguntaActual.opciones.isEmpty()) {
                Column(
                    modifier = Modifier
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "${testDelPescadorViewModel.preguntaActual.text}\n\nHas acertado: ${testDelPescadorViewModel.aciertos}")
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Button(onClick = { testDelPescadorViewModel.resetTest() }) {
                        Text("Reiniciar Test")
                    }
                }

            } else {
                Text(
                    text = testDelPescadorViewModel.preguntaActual.text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
                testDelPescadorViewModel.preguntaActual.opciones.forEach { respuesta ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)) {
                        RadioButton(
                            selected = false,
                            onClick = { testDelPescadorViewModel.enviarRespuesta(respuesta) }

                        )
                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = respuesta)
                    }
                }
            }
        }
    }
}