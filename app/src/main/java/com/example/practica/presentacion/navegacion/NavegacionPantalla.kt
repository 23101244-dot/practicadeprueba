
package com.example.practica.presentacion.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica.presentacion.pantallas.ActividadFisica
import com.example.practica.presentacion.pantallas.CalculadoraConsumoAgua
import com.example.practica.presentacion.pantallas.CatalogoAutos
import com.example.practica.presentacion.pantallas.menuPantallas
@Composable
fun GraficosPantalla() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            menuPantallas(navController = navController)
        }
        composable("agua") {
            CalculadoraConsumoAgua(navController=navController)
        }
        composable("actividad") {
            ActividadFisica(navController=navController)
        }
        composable("autos") {
            CatalogoAutos(navController=navController)
        }
    }
}