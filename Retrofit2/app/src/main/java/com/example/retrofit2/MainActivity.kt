@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.retrofit2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit2.model.Digimon
import com.example.retrofit2.ui.theme.Retrofit2Theme
import com.example.retrofit2.ui.view.HomeScreen
import com.example.retrofit2.ui.viewModel.DigimonViewModel

class MainActivity : ComponentActivity() {

    val digimonViewModel by viewModels<DigimonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Retrofit2Theme {
                Scaffold (
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Digimon Characters")
                            }
                        )
                    }
                ){paddingValues ->
                    Surface(
                        modifier = Modifier.padding(paddingValues),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        DigimonList(digimonList = digimonViewModel.digimonListResponce)
                        digimonViewModel.getDigimonList()
                    }
                }
            }
        }
    }
}

@Composable
fun DigimonList(
    digimonList: List<Digimon>
) {
    Column (
        modifier = Modifier
            .padding(10.dp)
    ){
 LazyColumn(
     modifier = Modifier
         .padding(15.dp)
 ) {
     itemsIndexed(items = digimonList) { index, digimon ->
         HomeScreen(digimon = digimon)
     }
 }
 }
}

