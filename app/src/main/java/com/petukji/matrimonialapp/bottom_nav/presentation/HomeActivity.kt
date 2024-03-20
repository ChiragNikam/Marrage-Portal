package com.petukji.matrimonialapp.bottom_nav.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.NetworkNotAvalbeActivity
import com.petukji.matrimonialapp.bottom_nav.data.util_data.BottomNavigationItem
import com.petukji.matrimonialapp.bottom_nav.domain.HomeViewModel
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.bottom_nav.components.BottomNav
import com.petukji.matrimonialapp.bottom_nav.components.NavigationForHome
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Master
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import com.petukji.matrimonialapp.bottom_nav.nav_items.status.domain.StatusViewModel
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    // Home view model
    private val homeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    private val statusViewModel by lazy { ViewModelProvider(this)[StatusViewModel::class.java] }

    override fun onStart() {
        super.onStart()

        //Check internet connection
        if (isNetworkAvailable()){
            val users = Users()
            val master = Master()

        }else{
            startActivity(Intent(this,NetworkNotAvalbeActivity::class.java))
            finish()
            Toast.makeText(this,"Internet connection issue.Please check your connection",Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null){
            lifecycleScope.launch {
                homeViewModel.loadUserPreferenceAndProfileData(profileKeyId = "11234567894")

                statusViewModel.getShortListedProfilesByMe("11234567894")
                statusViewModel.getProfilesViewedByMe("11234567894")
            }
        }

        setContent {
            // items for bottom navigation bar
            val bottomBarItems = listOf(
                BottomNavigationItem(
                    title = "Match",
                    route = "home",
                    selectedIcon = painterResource(id = R.drawable.filled_home),
                    unselectedIcon = painterResource(id = R.drawable.outline_home)
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

                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        NavigationForHome(
                            navHostController = navController,
                            homeViewModel,
                            statusViewModel = statusViewModel)
                    }
                }
            }
        }

    }
   fun isNetworkAvailable(): Boolean {
        val connectivityManager=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnected

    }
}