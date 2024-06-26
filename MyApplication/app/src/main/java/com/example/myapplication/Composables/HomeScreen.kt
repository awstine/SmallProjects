@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.data.Screen
import com.example.myapplication.data.hairList
import com.example.myapplication.data.menHair
import com.example.myapplication.data.womenHair
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    navController: NavController,
    onNavigationIconClick: () -> Unit,
    auth: FirebaseAuth,
) {
    var user by remember { mutableStateOf(auth.currentUser) }

    if (user == null) {
        LogInScreen(
            navController = navController,
            auth = auth,
            onSignedIn = { signedInUser ->
                user = signedInUser
            },
        )
    } else {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val configuration = LocalConfiguration.current
        // val drawerWidth = 0.8f * configuration.screenWidthDp.dp

        Surface(
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = true,
                drawerContent = {
                    DrawerContent(navController, onSignOut = { user = null })
                },
                content = {
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        },
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Our Saloon App")
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                        Log.d("HomeScreen", "Drawer opened")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null,
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null,
                                        )
                                    }
                                },
                            )
                        },
                        bottomBar = {
                            BottomBar(
                                onHomeClick = { navController.navigate(Screen.HomeScreen.route) },
                                onSearchClick = { scope.launch { snackbarHostState.showSnackbar("Search not implimented") } },
                                onFavoritesClick = { navController.navigate(Screen.FavouriteScreen.route) },
                            )
                        },
                    ) { paddingValues ->
                        MainContent(paddingValues)
                    }
                },
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun DrawerContent(
    navController: NavController,
    onSignOut: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxHeight()
                .width(0.8f * LocalConfiguration.current.screenWidthDp.dp)
                .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(Screen.HomeScreen.route)
            },
        ) {
            Text(text = "Home")
//
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(Screen.FavouriteScreen.route)
            },
        ) {
            Text(text = "Favourite")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(Screen.FavouriteScreen.route)
            },
        ) {
            Text(text = "Services")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
            },
        ) {
            Text(text = "Gallery")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
            },
        ) {
            Text(text = "Stylists")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSignOut() },
        ) {
            Text(text = "Sign Out")
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainContent(paddingValues: PaddingValues) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Trending Hairstyle",
            fontSize = 40.sp,
            modifier = Modifier.padding(9.dp),
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        )
        LazyRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(hairList) { hair ->
                Card(
                    modifier =
                        Modifier
                            .width(250.dp)
                            .height(350.dp),
                    shape = RoundedCornerShape(8.dp),
                    // elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = hair.img),
                            contentDescription = null,
                            modifier = Modifier,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = hair.hairName,
                                modifier = Modifier.padding(8.dp),
                            )
                            FavouriteButton()
                        }

//                        Text(
//                            text = "$${hair.hairPrice}",
//                            modifier = Modifier.padding(8.dp),
//                        )
                    }
                }
            }
        }
        Text(
            text = "Men",
            fontSize = 30.sp,
            modifier = Modifier.padding(9.dp),
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp,
            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
        )

        LazyRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(menHair) { Hair ->
                Card(
                    modifier =
                        Modifier
                            .width(250.dp)
                            .height(350.dp),
                    shape = RoundedCornerShape(8.dp),
                    // elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = Hair.img),
                            contentDescription = null,
                            modifier = Modifier,
                        )
                        Text(
                            text = Hair.hairName,
                            modifier = Modifier.padding(8.dp),
                        )
                        Text(
                            text = "$${Hair.hairPrice}",
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        }
        Text(
            text = "Women",
            fontSize = 30.sp,
            modifier = Modifier.padding(9.dp),
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp,
            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
        )

        LazyRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(womenHair) { hare ->
                Card(
                    modifier =
                        Modifier
                            .width(250.dp)
                            .height(350.dp),
                    shape = RoundedCornerShape(8.dp),
                    // elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = hare.img),
                            contentDescription = null,
                            modifier = Modifier,
                        )
                        Text(
                            text = hare.hairName,
                            modifier = Modifier.padding(8.dp),
                        )
                        Text(
                            text = "$${hare.hairPrice}",
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FavouriteButton() {
    var checked by remember { mutableStateOf(false) }
    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
    ) {
        val tint by animateColorAsState(
            if (checked) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
        )
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = tint,
        )
    }
}

// viewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun BottomBar(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit,
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically, // Align content vertically
        ) {
            BottomBarItem(
                icon = Icons.Default.Home,
                label = "Home",
                onClick = onHomeClick,
            )
            BottomBarItem(
                icon = Icons.Default.Search,
                label = "Search",
                onClick = onSearchClick,
            )
            BottomBarItem(
                icon = Icons.Default.Favorite,
                label = "Favorites",
                onClick = onFavoritesClick,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun BottomBarItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() },
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.padding(8.dp),
        )
        Spacer(modifier = Modifier.height(4.dp)) // Add space between icon and text
        Text(label)
    }
}
