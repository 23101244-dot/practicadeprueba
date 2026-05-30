package com.example.practica.presentacion.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadFisica(navController: NavController) {
    var actividad by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var expandido by remember { mutableStateOf(false) }

    val contexto = LocalContext.current
    val opcionesActividad = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga") //
    val opcionesIntensidad = listOf("Baja", "Media", "Alta") //

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Actividad Física", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Menú desplegable para Actividad
        ExposedDropdownMenuBox(
            expanded = expandido,
            onExpandedChange = { expandido = !expandido }
        ) {
            OutlinedTextField(
                value = actividad.ifEmpty { "Seleccione actividad" },
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de actividad") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandido,
                onDismissRequest = { expandido = false }
            ) {
                opcionesActividad.forEach { seleccion ->
                    DropdownMenuItem(
                        text = { Text(seleccion) },
                        onClick = {
                            actividad = seleccion
                            expandido = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para Duración
        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duración (minutos)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Selecciona la intensidad:")

        // Botones de radio para Intensidad
        opcionesIntensidad.forEach { opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (intensidad == opcion),
                    onClick = { intensidad = opcion }
                )
                Text(text = opcion)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Calcular
        Button(onClick = {
            val duracionNum = duracion.toIntOrNull()

            // Validaciones
            if (actividad.isBlank() || duracion.isBlank() || intensidad.isBlank()) {
                Toast.makeText(contexto, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else if (duracionNum == null || duracionNum <= 0) {
                Toast.makeText(contexto, "La duración debe ser entera positiva", Toast.LENGTH_SHORT).show() //
            } else {
                // Cálculo de calorías
                val calPorMin = when (actividad) {
                    "Correr" -> 10
                    "Caminar" -> 5
                    "Nadar" -> 8
                    "Ciclismo" -> 7
                    "Yoga" -> 4
                    else -> 0
                }

                val factor = when (intensidad) {
                    "Baja" -> 0.8
                    "Media" -> 1.0
                    "Alta" -> 1.2
                    else -> 1.0
                }

                val calorias = calPorMin * duracionNum * factor //
                resultado = "Quemaste un total de $calorias calorías."
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultado.isNotEmpty()) {
            Text(resultado, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para regresar
        Button(onClick = { navController.navigate("menu") }) {
            Text("Regresar al menú principal")
        }
    }
}