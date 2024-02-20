package com.petukji.marrageportal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petukji.marrageportal.DataClass.BottomNavigationItem
import com.petukji.marrageportal.Views.Home
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class HomeActivity : ComponentActivity() {
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
                        BottomNav(
                            items = bottomBarItems,
                            navController = navController,
                            onItemClicked = {
                                navController.navigate(it.route)
                            })
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer

                ) {
                    Surface(modifier = Modifier.fillMaxSize().padding(it)) {
                        NavigationForHome(navHostController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationForHome(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home()
        }
        composable("search") {
            Text(text = "Search", modifier = Modifier.fillMaxSize())
        }
        composable("Status") {
            Text(text = "Status", modifier = Modifier.fillMaxSize())
        }
        composable("Profile") {
            Text(text = "Profile", modifier = Modifier.fillMaxSize())
        }
    }
}