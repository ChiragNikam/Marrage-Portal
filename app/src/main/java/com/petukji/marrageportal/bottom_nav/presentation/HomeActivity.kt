package com.petukji.marrageportal.bottom_nav.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.petukji.marrageportal.bottom_nav.data.util_data.BottomNavigationItem
import com.petukji.marrageportal.bottom_nav.domain.HomeViewModel
import com.petukji.marrageportal.R
import com.petukji.marrageportal.bottom_nav.components.BottomNav
import com.petukji.marrageportal.bottom_nav.components.NavigationForHome
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterest
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocation
import com.petukji.marrageportal.bottom_nav.data.api_request.Master
import com.petukji.marrageportal.bottom_nav.data.api_request.Users
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class HomeActivity : ComponentActivity() {
    override fun onStart() {
        super.onStart()
        val users = Users()
        val master = Master()
        GlobalScope.launch {
            async {
                users.getUserPreference()
                users.getUserData()
                master.getMasterLocationData(locationRequest = MasterLocation(masterType = "degree",queryType = "read"))
                master.getMasterInterestData(interest = MasterInterest("interest", "read"))
            }
        }

    }

    // Home view model
    private val homeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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

            val currentDestination by homeViewModel.currentDestinationBottomNav.collectAsState()

            MarriagePortalTheme {

                // Scaffold for bottom bar
                Scaffold(
                    topBar = {

                    },
                    bottomBar = {
                        BottomNav(
                            items = bottomBarItems,
                            navController = navController
                        ) {
                            navController.navigate(it.route)
                        }

                    },
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->

                    val sheetState = rememberStandardBottomSheetState(
                        skipHiddenState = false
                    )
                    val scaffoldState = rememberBottomSheetScaffoldState(
                        bottomSheetState = sheetState
                    )
                    val scope = rememberCoroutineScope()

                    val bottomPadding = innerPadding.calculateBottomPadding() + 40.dp

                    // Scaffold for bottom sheet
                    BottomSheetScaffold(
                        modifier = Modifier,
                        scaffoldState = scaffoldState,
                        sheetPeekHeight = if (currentDestination == "home") {
                            bottomPadding
                        } else 0.dp,
                        sheetContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        sheetContent = {
                            Surface(color = MaterialTheme.colorScheme.primaryContainer) {
                                BottomSheetContent(bottomPadding)
                            }
                        }
                    ) {
                        LaunchedEffect(currentDestination) {
                            scope.launch {
                                if (currentDestination != "home" && currentDestination != "search")
                                    if (sheetState.hasExpandedState) {
                                        sheetState.hide()
                                    }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            NavigationForHome(
                                navHostController = navController,
                                homeViewModel,
                                onSearch = {
                                    scope.launch {
                                        sheetState.expand()
                                    }
                                })
                        }
                    }
                }
            }
        }


    }


}
