@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.retrofit1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retrofit1.data.Screens
import com.example.retrofit1.ui.home.DetailScreen
import com.example.retrofit1.ui.home.HomeScreen
import com.example.retrofit1.ui.home.HomeViewmodel
import com.example.retrofit1.ui.theme.Retrofit1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var homeViewmodel: HomeViewmodel
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            homeViewmodel = hiltViewModel()
            Retrofit1Theme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = Screens.HomeScreen.route){
                    composable(Screens.HomeScreen.route){
                        HomeScreen(homeViewmodel = homeViewmodel,navController)
                    }
                    composable("detailScreen" +
                            "/{characterName}/{characterActor}/{characterImage}",
                        arguments = listOf(
                            navArgument("characterName"){
                                type = NavType.StringType
                            },
                            navArgument("characterActor"){
                                type = NavType.StringType
                            },
                            navArgument("characterImage"){
                                type = NavType.StringType
                            },
                        )
                    ){backStackEntry ->
                        val characterName = backStackEntry.arguments?.getString("characterName")
                        val characterActor = backStackEntry.arguments?.getString("characterActor")
                        val characterImage = backStackEntry.arguments?.getString("characterImage")
                        DetailScreen(navController,characterName,characterActor,characterImage)
                    }
                }
            }
        }
    }
}
