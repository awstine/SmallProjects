@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.viewModel.FavouritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun FavouriteScreen(
    navController: NavController,
    favouritesViewModel: FavouritesViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favourites")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (favouritesViewModel.favourites.isEmpty()) {
                Text(
                    text = "You don't have any favorites yet.",
                    modifier = Modifier,
                    fontSize = 20.sp,
                )
            } else {
                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                ) {
                    items(favouritesViewModel.favourites) { hair ->
                        Card(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Image(
                                    painter = painterResource(id = hair.img),
                                    contentDescription = null,
                                    modifier = Modifier,
                                )
                                Text(
                                    text = hair.hairName,
                                    modifier = Modifier.padding(8.dp),
                                )
                                Text(
                                    text = "${hair.hairPrice}",
                                    modifier = Modifier.padding(8.dp),
                                )
//
                            }

//
                        }
                    }

//
                }
            }
        }
    }
}
