package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.noteapp.Screens.AddNoteScreen
import com.example.noteapp.Screens.NotesScreen
import com.example.noteapp.data.NoteDatabase
import com.example.noteapp.presentation.NotesViewModel
import com.example.noteapp.ui.theme.NoteAppTheme

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    
    private val database by lazy{
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    //ViewModel factory
    private val viewModel by viewModels<NotesViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun<T: ViewModel > create(modelClass: Class<T>): T {
                    return NotesViewModel(database.dao) as T
                }

            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {

                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "NotesScreen"
                ){
                    composable("NotesScreen"){
                        NotesScreen(
                            state = state,
                            navController= navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable("AddNoteScreen"){
                        AddNoteScreen(
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}

