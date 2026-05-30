package com.example.practica.presentacion.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


data class AutoModel(
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imageUrl: String
)


val mockAutos = listOf(
    AutoModel("Ferrari", "F8", 280000.0, "https://www.themilliardaire.com/en/wp-content/uploads/2013/08/Ferrari-458-Speciale-Francfort-31.jpg"),
    AutoModel("Porsche", "911", 110000.0, "https://www.shutterstock.com/image-vector/luxury-premium-realistic-sedan-coupe-260nw-2264894613.jpg"),
    AutoModel("Lamborghini", "Huracán", 210000.0, "https://www.shutterstock.com/image-photo/lamborghini-aventador-s-sports-car-260nw-2509788631.jpg"),
    AutoModel("McLaren", "720S", 300000.0, "https://www.senna.com/wp-content/uploads/2017/12/Hero-ext_front-3_4_dark__doors_FINAL-1.jpg-2-1-scaled.jpg"),
    AutoModel("Audi", "R8", 150000.0, "https://www.shutterstock.com/image-photo/st-petersburg-russia-september-19-600nw-2045174033.jpg")
)

@Composable
fun CatalogoAutos(navController: NavController) {

    val costoTotal = mockAutos.sumOf { it.precio }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Catálogo de Autos", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(modifier = Modifier.weight(1f)) {
            items(mockAutos) { auto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            contentDescription = auto.modelo,
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop,
                            painter = rememberAsyncImagePainter(auto.imageUrl)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(auto.marca, style = MaterialTheme.typography.titleMedium)
                            Text("Modelo: ${auto.modelo}")
                            Text("Precio: $${auto.precio}")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Costo Total: $$costoTotal", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("menu") }) {
            Text("Regresar al menú")
        }
    }
}