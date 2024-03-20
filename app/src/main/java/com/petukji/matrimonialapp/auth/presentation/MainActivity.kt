package com.petukji.matrimonialapp.auth.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.auth.NavigationForAuth
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel
import com.petukji.matrimonialapp.bottom_nav.presentation.HomeActivity
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme

class MainActivity : ComponentActivity() {
    private val authViewModel by lazy { ViewModelProvider(this)[PersonalDetailsViewModel::class.java] }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            // finish activity if registration successful
            MarriagePortalTheme {
                val isRegistrationSuccess = authViewModel.isRegistrationSuccess.collectAsState()
                if (isRegistrationSuccess.value) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                // nav controller
                val navController = rememberNavController()
                NavigationForAuth(navHostController = navController, viewModel = authViewModel)
            }
        }
    }
}
