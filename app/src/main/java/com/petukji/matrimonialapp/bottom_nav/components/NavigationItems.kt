package com.petukji.matrimonialapp.bottom_nav.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.petukji.matrimonialapp.bottom_nav.data.util_data.BottomNavigationItem
import com.petukji.matrimonialapp.bottom_nav.nav_items.home.presentation.Home
import com.petukji.matrimonialapp.Views.RequestReceivedScreen
import com.petukji.matrimonialapp.bottom_nav.nav_items.search.presentation.SearchScreen
import com.petukji.matrimonialapp.bottom_nav.domain.HomeViewModel
import com.petukji.matrimonialapp.bottom_nav.domain.StatusViewModel


@Composable
fun NavigationForHome(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    statusViewModel: StatusViewModel
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            homeViewModel.updateCurrentDestinationBottomNav("home")
            Home(viewModel = homeViewModel,navController=navHostController)
        }

        composable("search") {
            homeViewModel.updateCurrentDestinationBottomNav("search")
          SearchScreen(viewModel = homeViewModel, modifier = Modifier.padding(bottom = 40.dp)) {
              navHostController.popBackStack()
          }
        }

        composable("status") {
            homeViewModel.updateCurrentDestinationBottomNav("status")
            RequestReceivedScreen(
                modifier = Modifier
                    .fillMaxSize(),
                statusViewModel
            )
        }
        composable("profile") {
            homeViewModel.updateCurrentDestinationBottomNav("profile")
            Text(text = "Profile", modifier = Modifier.fillMaxSize())
        }
    }
}

// Bottom Navigation Bar for Home Screen
@Composable
fun BottomNav(
    items: List<BottomNavigationItem>,
    navController: NavController,
    onItemClicked: (BottomNavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.shadow(8.dp),
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) { // to remember back state
        val backStackEntry = navController.currentBackStackEntryAsState()

        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = bottomNavigationItem.route == backStackEntry.value?.destination?.route,
                onClick = {
                    onItemClicked(bottomNavigationItem)
                },
                label = {
                    if (bottomNavigationItem.route == backStackEntry.value?.destination?.route) {
                        Text(
                            text = bottomNavigationItem.title,
                            fontWeight = FontWeight(900),
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Text(
                            text = bottomNavigationItem.title,
                            fontWeight = FontWeight(700),
                            color = Color.Gray
                        )
                    }
                },
                alwaysShowLabel = true,
                icon = {
                    if (bottomNavigationItem.route == backStackEntry.value?.destination?.route) {
                        Icon(
                            bottomNavigationItem.selectedIcon,
                            contentDescription = bottomNavigationItem.title,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Icon(
                            painter = bottomNavigationItem.unselectedIcon,
                            contentDescription = bottomNavigationItem.title,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
    }
}