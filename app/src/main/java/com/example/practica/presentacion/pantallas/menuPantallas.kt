package com.example.practica.presentacion.pantallas

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun menuPantallas(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                navController.navigate("AGUA")
            }
        ){Text("Calculadora de Consumo de agua")}



        Button(
            onClick = {
                navController.navigate("ACTIVIDAD")
            }
        ){Text("Registro de Atividad Fisica")}



        Button(
        onClick = {
            navController.navigate("AUTOS")
        }
        ){Text("Catalogo de autos deportivos")}


    }


}



