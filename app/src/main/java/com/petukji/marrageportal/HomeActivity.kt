package com.petukji.marrageportal

import android.os.Bundle
import android.provider.MediaStore.Images
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petukji.marrageportal.DataClass.BottomNavigationItem
import com.petukji.marrageportal.Views.Home
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class HomeActivity : ComponentActivity() {

    // Home view model
    private val homeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // items for bottom navigation bar
            val bottomBarItems = listOf(
                BottomNavigationItem(
                    title = "Home",
                    route = "home",
                    selectedIcon = painterResource(id = R.drawable.filled_home),
                    unselectedIcon = painterResource(id = R.drawable.outline_home)
                ),
                BottomNavigationItem(
                    title = "Search",
                    route = "search",
                    selectedIcon = painterResource(id = R.drawable.filled_search),
                    unselectedIcon = painterResource(id = R.drawable.filled_search)
                ),
                BottomNavigationItem(
                    title = "Status", route = "status", selectedIcon = painterResource(
                        id = R.drawable.filled_home
                    ),
                    unselectedIcon = painterResource(id = R.drawable.filled_home)
                ),
                BottomNavigationItem(
                    title = "Profile",
                    route = "profile",
                    selectedIcon = painterResource(id = R.drawable.filled_profile),
                    unselectedIcon = painterResource(id = R.drawable.outline_profile)
                )
            )

            // nav controller
            val navController = rememberNavController()


            MarriagePortalTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {

                    },
                    bottomBar = {
                        Column(
                            modifier = Modifier
//                                .background(Color.Transparent)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val showGirls by homeViewModel.showGirls.collectAsState()
                            if (!showGirls)
                                IconButton(
                                    onClick = {
                                        homeViewModel.updateShowGirls(true)
                                    }) {
                                    Icon(
                                        modifier = Modifier.border(
                                            BorderStroke(1.dp, Color.Black),
                                            shape = CircleShape
                                        ),
                                        imageVector = Icons.Outlined.KeyboardArrowUp,
                                        contentDescription = "show girls",
                                        tint = Color.Black
                                    )
                                }
                            BottomNav(
                                items = bottomBarItems,
                                navController = navController,
                                onItemClicked = {
                                    navController.navigate(it.route)
                                })
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer

                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavigationForHome(navHostController = navController, homeViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationForHome(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home(viewModel = homeViewModel)
        }
        composable("search") {
            Text(text = "Search", modifier = Modifier.fillMaxSize())
        }
        composable("status") {
            Text(text = "Status", modifier = Modifier.fillMaxSize())
        }
        composable("profile") {
            Text(text = "Profile", modifier = Modifier.fillMaxSize())
        }
    }
}