package com.petukji.marrageportal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petukji.marrageportal.Views.RequestReceivedScreen
import com.petukji.marrageportal.bottom_nav.components.AvailableGirlsVerticalGrid
import com.petukji.marrageportal.bottom_nav.components.BottomNav
import com.petukji.marrageportal.bottom_nav.components.DragableScreen
import com.petukji.marrageportal.bottom_nav.components.SingleGirlView
import com.petukji.marrageportal.bottom_nav.data.util_data.BottomNavigationItem
import com.petukji.marrageportal.bottom_nav.domain.HomeViewModel
import com.petukji.marrageportal.bottom_nav.home.presentation.Home
import com.petukji.marrageportal.bottom_nav.search.SearchScreen
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {

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
                        id = R.drawable.icon_profile_status
                    ),
                    unselectedIcon = painterResource(id = R.drawable.icon_profile_status)
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
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {

                    },
                    bottomBar = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BottomNav(
                                items = bottomBarItems,
                                navController = navController
                            ) {
                                navController.navigate(it.route)
                            }
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->

                    val sheetState = rememberStandardBottomSheetState(
//                        initialValue = SheetValue.PartiallyExpanded,
                        skipHiddenState = false
                    )
                    val scaffoldState = rememberBottomSheetScaffoldState(
                        bottomSheetState = sheetState
                    )
                    val scope = rememberCoroutineScope()

                    val bottomPadding = innerPadding.calculateBottomPadding() + 40.dp

                    val showResults by homeViewModel.showSearchResults.collectAsState()


                    BottomSheetScaffold(
                        modifier = Modifier,
                        scaffoldState = scaffoldState,
                        sheetPeekHeight = if (currentDestination == "home") {
                            bottomPadding
                        } else 0.dp,
                        sheetContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        sheetContent = {
                            Surface(color = MaterialTheme.colorScheme.primaryContainer) {
                                Column {
                                    var showSingleGirlView by rememberSaveable {
                                        mutableStateOf(false)
                                    }
                                    Row {
                                        IconButton(
                                            onClick = {
                                                showSingleGirlView = !showSingleGirlView
                                            }) {
                                            Icon(
                                                modifier = Modifier.size(32.dp),
                                                painter = painterResource(id = R.drawable.icon_cards),
                                                contentDescription = "Cards Icon"
                                            )
                                        }
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                    AnimatedVisibility(
                                        visible = showSingleGirlView,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f))
                                    ) {
                                        val pagerState = rememberPagerState(pageCount = { 3 })

                                        HorizontalPager(
                                            modifier = Modifier.padding(bottom = bottomPadding),
                                            state = pagerState
                                        ) {
                                            SingleGirlView(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = 40.dp, vertical = 20.dp),
                                                imageModifier = Modifier.fillMaxHeight()
                                            )
                                        }
                                    }
                                    AnimatedVisibility(
                                        visible = !showSingleGirlView,
                                    ) {
                                        AvailableGirlsVerticalGrid(
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                end = 20.dp
                                            )
                                        )
                                    }

                                }
                            }
                        }
                    ) {
                        LaunchedEffect(currentDestination) {
                            scope.launch {
                                if (currentDestination != "home")
                                    if (sheetState.hasExpandedState) {
                                        sheetState.hide()
                                    }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            NavigationForHome(navHostController = navController, homeViewModel)
                        }
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
            homeViewModel.updateCurrentDestinationBottomNav("home")
            Home(viewModel = homeViewModel)
        }
        composable("search") {
            homeViewModel.updateCurrentDestinationBottomNav("search")
            DragableScreen(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SearchScreen(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize(), viewModel = homeViewModel
                ) {}
            }
        }
        composable("status") {
            homeViewModel.updateCurrentDestinationBottomNav("status")
            RequestReceivedScreen(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            )
        }
        composable("profile") {
            homeViewModel.updateCurrentDestinationBottomNav("profile")
            Text(text = "Profile", modifier = Modifier.fillMaxSize())
        }
    }
}
