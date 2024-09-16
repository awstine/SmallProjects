@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Hair
import com.example.myapplication.data.MenuDrawerItem2
import com.example.myapplication.data.Screen
import com.example.myapplication.data.hairList
import com.example.myapplication.data.menHair
import com.example.myapplication.data.womenHair
import com.example.myapplication.viewModel.FavouritesViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    navController: NavController,
    onNavigationIconClick: () -> Unit,
    auth: FirebaseAuth,
    favoritesViewModel: FavouritesViewModel = viewModel(),
) {
    var user by remember { mutableStateOf(auth.currentUser) }
    var username by remember { mutableStateOf("") }
    val isUserLoggedIn = user != null

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val configuration = LocalConfiguration.current

    if (!isUserLoggedIn) {
        LogInScreen(
            navController = navController,
            auth = auth,
            onSignedIn = { signedInUser ->
                user = signedInUser
                signedInUser?.uid?.let { userId ->
                    fetchUserName(userId) { fetchedName ->
                        username = fetchedName
                    }
                }
            },
        )
    } else {
        user?.uid?.let { userId ->
            fetchUserName(userId) { fetchedName ->
                username = fetchedName
            }
        }

        Surface(
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            NavDrawer(
                navController = navController,
                onNavigationIconClick = onNavigationIconClick,
                auth = auth,
                favoritesViewModel = favoritesViewModel,
            )
//            ModalNavigationDrawer(
//                drawerState = drawerState,
//                gesturesEnabled = true,
//                drawerContent = {
//                    DrawerContent(navController, onSignOut = { user = null })
//                },
//                content = {
//                    Scaffold(
//                        snackbarHost = {
//                            SnackbarHost(hostState = snackbarHostState)
//                        },
//                        topBar = {
//                            TopAppBar(
//                                title = {
//                                    Text(text = "Our Saloon App")
//                                },
//                                navigationIcon = {
//                                    IconButton(onClick = {
//                                        scope.launch {
//                                            drawerState.open()
//                                        }
//                                        Log.d("HomeScreen", "Drawer opened")
//                                    }) {
//                                        Icon(
//                                            imageVector = Icons.Default.Menu,
//                                            contentDescription = null,
//                                        )
//                                    }
//                                },
//                                actions = {
//                                    IconButton(onClick = {}) {
//                                        Icon(
//                                            imageVector = Icons.Default.Search,
//                                            contentDescription = null,
//                                        )
//                                    }
//                                },
//                            )
//                        },
//                        bottomBar = {
//                            BottomBar(
//                                onHomeClick = { navController.navigate(Screen.HomeScreen.route) },
//                                onSearchClick = { scope.launch { snackbarHostState.showSnackbar("Search not implemented") } },
//                                onFavoritesClick = { navController.navigate(Screen.FavouriteScreen.route) },
//                            )
//                        },
//                    ) { paddingValues ->
//                        MainContent(paddingValues, favoritesViewModel, username)
//                    }
//                },
//            )
        }
    }
}

fun fetchUserName(
    userId: String,
    onComplete: (String) -> Unit,
) {
    val db = Firebase.firestore
    db
        .collection("users")
        .document(userId)
        .get()
        .addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val firstName = document.getString("firstName") ?: ""
                val lastName = document.getString("lastName") ?: ""
                onComplete("$firstName $lastName")
            }
        }.addOnFailureListener { e ->
            Log.w("fetchUser", "Error fetching", e)
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun NavDrawer(
    navController: NavController,
    onNavigationIconClick: () -> Unit,
    auth: FirebaseAuth,
    favoritesViewModel: FavouritesViewModel = viewModel(),
) {
    val drawerItems =
        listOf(
            MenuDrawerItem2("Profile", 0, false, onClick = { }),
            MenuDrawerItem2("Home", 0, false, onClick = { navController.navigate(Screen.HomeScreen.route) }),
            MenuDrawerItem2("Services", 0, false, onClick = {}),
            MenuDrawerItem2("Gallery", 0, false, onClick = {}),
            MenuDrawerItem2("Stylists", 0, false, onClick = { navController.navigate(Screen.StylistScreen.route) }),
            MenuDrawerItem2("Notifications", 6, true, onClick = {}),
            MenuDrawerItem2("Sign Out", 0, false, onClick = { navController.navigate(Screen.LogInScreen.route) }),
        )

    var selectedItem by remember { mutableStateOf(drawerItems[0]) }
    var username by remember { mutableStateOf("") }
    var user by remember { mutableStateOf(auth.currentUser) }
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    // .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color.Transparent),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .wrapContentSize(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_3),
                                contentDescription = "Profile Pic",
                                modifier =
                                    Modifier
                                        .size(120.dp)
                                        .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                            )
                            Text(
                                text = "Mrs Belair",
                                fontSize = 20.sp,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 15.dp),
                                textAlign = TextAlign.Center,
                            )
                        }

                        Divider(
                            modifier =
                                Modifier
                                    .align(Alignment.BottomCenter),
                            thickness = 1.dp,
                            Color.DarkGray,
                        )
                    }

                    DrawerContent(navController, onSignOut = { user = null })

//                    drawerItems.forEach {
//                        NavigationDrawerItem(
//                            label = { Text(text = it.title) },
//                            selected = it == selectedItem,
//                            onClick = {
//                                selectedItem = it
//                                // When an item is clicked in the drawer..the drawer closes as  u navigate to the screen
//                                scope.launch {
//                                    drawerState.close()
//                                }
//                            },
//                            modifier =
//                                Modifier
//                                    .padding(horizontal = 23.dp),
//                            badge = {
//                                if (it.hasBadge) {
//                                    BadgedBox(
//                                        badge = {
//                                            Badge {
//                                                Text(
//                                                    text = it.badgeCount.toString(),
//                                                    fontSize = 17.sp,
//                                                )
//                                            }
//                                        },
//                                    ) {
//
//                                    }
//                                }
//                            },
//                        )
//                    }
                }
            }
        },
        drawerState = drawerState,
    ) {
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
                    onSearchClick = { scope.launch { snackbarHostState.showSnackbar("Search not implemented") } },
                    onFavoritesClick = { navController.navigate(Screen.FavouriteScreen.route) },
                )
            },
        ) { paddingValues ->
            // MainContent(paddingValues, favouritesViewModel, userName)
            MainContent(paddingValues = paddingValues, favouritesViewModel = favoritesViewModel, username)
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
                .fillMaxWidth()
                // .width(0.8f * LocalConfiguration.current.screenWidthDp.dp)
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
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
                navController.navigate(Screen.StylistScreen.route)
            },
        ) {
            Text(text = "Stylists")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onSignOut()
            },
        ) {
            Text(text = "Sign Out")
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainContent(
    paddingValues: PaddingValues,
    // When user logs in
    favouritesViewModel: FavouritesViewModel,
    userName: String,
) {
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
            text = "Welcome",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = userName,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.ExtraBold,
        )
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
                OutlinedCard(
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .width(250.dp)
                            .height(350.dp),
                    shape = RoundedCornerShape(8.dp),
                    border =
                        BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                        ),
                    elevation = CardDefaults.cardElevation(5.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = hair.img),
                            contentDescription = null,
                            modifier = Modifier.aspectRatio(1.0f),
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = hair.hairName,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier =
                                    Modifier
                                        .padding(
                                            top = 10.dp,
                                        ),
                            )
                            FavouriteButton(hair, favouritesViewModel)
                        }
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
                OutlinedCard(
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .width(250.dp)
                            .height(350.dp),
                    shape = RoundedCornerShape(8.dp),
                    border =
                        BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                        ),
                    elevation = CardDefaults.cardElevation(5.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = Hair.img),
                            contentDescription = null,
                            modifier = Modifier.aspectRatio(1.0f),
                        )
                        Text(
                            text = Hair.hairName,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier =
                                Modifier
                                    .padding(
                                        top = 10.dp,
                                        end = 10.dp,
                                        bottom = 12.dp,
                                    ),
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
                OutlinedCard(
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .width(250.dp)
                            .height(350.dp),
                    shape = RoundedCornerShape(8.dp),
                    border =
                        BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                        ),
                    elevation = CardDefaults.cardElevation(5.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = hare.img),
                            contentDescription = null,
                            modifier = Modifier.aspectRatio(1.0f),
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
fun FavouriteButton(
    hair: Hair,
    favouritesViewModel: FavouritesViewModel = viewModel(),
) {
    var checked by remember { mutableStateOf(favouritesViewModel.favourites.contains(hair)) }
    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            checked = it
            if (checked) {
                favouritesViewModel.addFavourite(hair)
            } else {
                favouritesViewModel.removeFavourite(hair)
            }
        },
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
