package com.petukji.matrimonialapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.Views.Login
import com.petukji.matrimonialapp.auth.NavigationForAuth
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel
import com.petukji.matrimonialapp.bottom_nav.presentation.HomeActivity
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme

class MainActivity : ComponentActivity() {
    val authViewModel by lazy {ViewModelProvider(this)[PersonalDetailsViewModel::class.java]}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarriagePortalTheme {
                // nav controller
                val navController = rememberNavController()
                NavigationForAuth(navHostController = navController, viewModel = authViewModel)
            }
        }
    }
}
