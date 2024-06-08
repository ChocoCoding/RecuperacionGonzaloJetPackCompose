package com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.lista_pesca
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.ArticulosPesca
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.entities.getListaArticulosPesca
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.screens.Screens
import com.example.recuperacion_gonzalo_campos_jetpackcompose.ui.theme.viewmodel.ListadoPescaViewModel

@Composable
fun ComponenteArticulo(
    articulo: ArticulosPesca,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
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

        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(text = "${articulo.nombre}\n\nPrecio: ${articulo.precio}€", modifier = Modifier.padding(start = 16.dp))
    }
}


@Composable
fun ListaArticulos(
    articulos: List<ArticulosPesca>,
    articulosSeleccionados: List<ArticulosPesca>,
    onArticuloSeleccionadoChange: (ArticulosPesca, Boolean) -> Unit
) {
    LazyColumn {
        items(articulos) { articulo ->
            val isSelected = articulosSeleccionados.contains(articulo)
            ComponenteArticulo(
                articulo = articulo,
                isSelected = isSelected
            ) { seleccionado ->
                onArticuloSeleccionadoChange(articulo, seleccionado)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun App(navController: NavController,viewModelListadoPesca: ListadoPescaViewModel) {
    var seleccionarTodos by remember { mutableStateOf(false) }
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
                    Row {
                        Checkbox(
                            checked = seleccionarTodos,
                            onCheckedChange = {isChecked ->
                                seleccionarTodos = isChecked
                                viewModelListadoPesca.seleccionarTodos(isChecked)
                            })
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Costo Total: ${viewModelListadoPesca.articulosSeleccionados.value.sumByDouble { it.precio }}€")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {


            ListaArticulos(
                articulos = getListaArticulosPesca(),
                articulosSeleccionados = viewModelListadoPesca.articulosSeleccionados.value,
                onArticuloSeleccionadoChange = { articulo, seleccionado ->
                    viewModelListadoPesca.onArticuloSeleccionadoChange(articulo,seleccionado)
                }
            )
        }
    }
}

@Composable
fun CarritoCompraIcono(navController: NavController,viewModelListadoPesca: ListadoPescaViewModel) {

    IconButton(onClick = { navController.navigate(Screens.Carrito.route) }) {
        Icon(Icons.Default.ShoppingCart,contentDescription = "Carrito de compra")
        if (viewModelListadoPesca.contadorArticulos.value > 0) {
            Badge(valor = viewModelListadoPesca.contadorArticulos.value)
        }
    }
}

@Composable
fun Badge(valor: Int) {
    Surface(
        modifier = Modifier.padding(start = 30.dp, top = 20.dp)
    ) {
        Text(
            text = valor.toString(),
            modifier = Modifier.padding(1.dp)
        )
    }
}