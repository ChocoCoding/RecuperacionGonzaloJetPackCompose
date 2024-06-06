package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.lista_pesca

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.ArticulosPesca
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.ListadoPescaViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun AppCarrito(navController: NavController, viewModelListadoPesca: ListadoPescaViewModel){
    var context = LocalContext.current

    fun confirmarCompra()= viewModelListadoPesca.confirmarCompra();

    fun realizarCompra() {
        viewModelListadoPesca.resetearArticulosSeleccionados()
        Toast.makeText(context, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Articulos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    CarritoCompraIcono(navController = navController, viewModelListadoPesca = viewModelListadoPesca)

                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Costo Total: ${viewModelListadoPesca.articulosSeleccionados.value.sumByDouble { it.precio }}€")
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text("Articulos Seleccionados: ${viewModelListadoPesca.contadorArticulos.value}")
            Button(onClick = {confirmarCompra()}) {
                Text("Confirmar Compra")
            }
            Spacer(modifier = Modifier.height(8.dp))
            ListaArticulosSeleccionados(
                articulosSeleccionados = viewModelListadoPesca.articulosSeleccionados.value,
                viewModel = viewModelListadoPesca
            )
            


            if(viewModelListadoPesca.showDialog.value){
                AlertDialog(
                    onDismissRequest = {viewModelListadoPesca.showDialog.value = false},
                    title = { Text(text = "Confirmar Compra")},
                    text = { Text("¿Deseas realizar la compra?\nCoste total: $${viewModelListadoPesca.articulosSeleccionados.value.sumByDouble { it.precio }}")

                           },
                    confirmButton = {
                        Button(onClick = {
                            realizarCompra()
                            viewModelListadoPesca.showDialog.value = false
                        }) {
                            Text(text = "Si")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {viewModelListadoPesca.showDialog.value = false}) {
                            Text(text = "Cancelar")
                        }
                    })
            }

        }

    }

}


@Composable
fun ComponenteArticulo2(
    articulo: ArticulosPesca,
    isSelected: Boolean,
    viewModel: ListadoPescaViewModel
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Image(painter = painterResource(id = articulo.imagenResId),
            contentDescription = "Imagen de ${articulo.nombre}",
            modifier = Modifier
                .size(128.dp)
                .padding(start = 16.dp, end = 16.dp))

        Row {
            Text(text = "${articulo.nombre}\n\nPrecio: ${articulo.precio}€", modifier = Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {viewModel.eliminarArticulo(articulo = articulo)}) {
                Icon(Icons.Default.Delete,contentDescription = "Eliminar")
            }
        }
    }
}



@Composable
fun ListaArticulosSeleccionados(
    articulosSeleccionados: List<ArticulosPesca>,
    viewModel: ListadoPescaViewModel
) {
    LazyColumn {
        items(articulosSeleccionados) { articulo ->
            val isSelected = articulosSeleccionados.contains(articulo)
            ComponenteArticulo2(
                articulo = articulo,
                isSelected = isSelected,
                viewModel = viewModel
            )
        }
    }
}
