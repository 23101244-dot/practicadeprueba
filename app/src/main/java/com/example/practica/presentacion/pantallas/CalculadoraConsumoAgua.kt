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

@Composable
fun CalculadoraConsumoAgua(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("Sin especificar") }
    var resultado by remember { mutableStateOf("") }

    val contexto = LocalContext.current
    val opcionesGenero = listOf("Masculino", "Femenino", "Sin especificar")

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Consumo de Agua", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo para Peso
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso corporal (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Selecciona tu género:")

        // Botones de radio para Género
        opcionesGenero.forEach { opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (genero == opcion),
                    onClick = { genero = opcion }
                )
                Text(text = opcion)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Calcular
        Button(onClick = {
            val pesoNum = peso.toFloatOrNull()

            // Validaciones
            if (nombre.isBlank() || peso.isBlank()) {
                Toast.makeText(contexto, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else if (pesoNum == null || pesoNum < 5f || pesoNum > 200f) {
                Toast.makeText(contexto, "El peso debe estar entre 5 y 200", Toast.LENGTH_SHORT).show()
            } else {
                // Cálculo
                val factor = when (genero) {
                    "Masculino" -> 1.02
                    "Femenino" -> 1.01
                    else -> 1.00
                }
                val litros = pesoNum * 0.035 * factor
                val litrosFormateado = String.format("%.2f", litros)
                resultado = "$nombre debe beber aproximadamente $litrosFormateado litros de agua al día"
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto del resultado
        if (resultado.isNotEmpty()) {
            Text(resultado, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para regresar al menú
        Button(onClick = { navController.navigate("menu") }) {
            Text("Regresar al menú principal")
        }
    }
}