package com.petukji.matrimonialapp.auth.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.petukji.matrimonialapp.auth.NavigationForAuth
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import com.petukji.matrimonialapp.bottom_nav.presentation.HomeActivity
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private val authViewModel by lazy { ViewModelProvider(this)[PersonalDetailsViewModel::class.java] }

    override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            lifecycleScope.launch {
                val isUserRegistered = lifecycleScope.async { authViewModel.checkUserRegistered() }.await()
                if (isUserRegistered) {
                    Log.d("user_data", currentUser.phoneNumber.toString())
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    finish()
                } else{
                    authViewModel.updateUserRegistered(false)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            // finish activity if registration successful
            MarriagePortalTheme {
                val isRegistrationSuccess by authViewModel.isRegistrationSuccess.collectAsState()
                val isUserRegistered by authViewModel.isUserRegistered.collectAsState()
                if (isRegistrationSuccess || isUserRegistered) {
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
