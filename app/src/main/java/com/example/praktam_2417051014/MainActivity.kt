package com.example.praktam_2417051014

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praktam_2417051014.model.MapelSource
import com.example.praktam_2417051014.ui.theme.PrakTAM_2417051014Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051014Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Karina",
                        npm = "2417051014",
                        ide = "BasicKuiz-Latihan Soal untuk SMA",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    npm: String,
    ide: String,
    modifier: Modifier = Modifier
) {

    val listMapel = MapelSource.dummyMapel

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Column {
                Text(text = "Hallo, saya $name", style = MaterialTheme.typography.titleMedium)
                Text(text = "NPM: $npm")
                Text(text = "Ide: $ide")
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        items(listMapel) { mapel ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Image(
                        painter = painterResource(id = mapel.imageRes),
                        contentDescription = mapel.nama,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Nama Mapel: ${mapel.nama}")
                    Text(text = "Deskripsi: ${mapel.deskripsi}")
                    Text(text = "Kelas: ${mapel.kelas}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM_2417051014Theme {
        Greeting(
            name = "Karina",
            npm = "2417051014",
            ide = "BasicKuiz-Latihan Soal untuk SMA"
        )
    }
}