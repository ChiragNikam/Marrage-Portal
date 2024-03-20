package com.petukji.matrimonialapp.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel
import com.petukji.matrimonialapp.auth.presentation.AdditionalDetailScreen
import com.petukji.matrimonialapp.auth.presentation.FamilyDetailScreen
import com.petukji.matrimonialapp.auth.presentation.ForWhomScreen
import com.petukji.matrimonialapp.auth.presentation.LoginScreen
import com.petukji.matrimonialapp.auth.presentation.PersonalDetailsForm
import com.petukji.matrimonialapp.auth.presentation.PickUpYourFav
import com.petukji.matrimonialapp.auth.presentation.QualificationScreen
import com.petukji.matrimonialapp.auth.presentation.UploadImage

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationForAuth(
    navHostController: NavHostController,
    viewModel: PersonalDetailsViewModel
) {
    val isUserRegistered by viewModel.isUserRegistered.collectAsState()
    NavHost(
        navController = navHostController,
        startDestination = if (!isUserRegistered && FirebaseAuth.getInstance().currentUser != null) "personal_details" else "login"
    ) {
        composable("login") {
            LoginScreen(
                navController = navHostController,
                viewModel = viewModel,
                onRequestOtp = { }) {

            }
        }

        composable("personal_details") {
            PersonalDetailsForm(viewModel = viewModel, navController = navHostController)
        }

        composable("address") {
            AdditionalDetailScreen(navController = navHostController, viewModel = viewModel)
        }

        composable("pick_fav") {
            PickUpYourFav(navController = navHostController)
        }

        composable("qualification") {
            QualificationScreen(navController = navHostController, viewModel = viewModel)
        }

        composable("uploadImage") {
            UploadImage(navController = navHostController, viewModel)
        }


        composable("family_details") {
            FamilyDetailScreen(navController = navHostController, viewModel = viewModel)
        }

        composable("for_whom") {
            ForWhomScreen(navHostController, viewModel)
        }

    }
}