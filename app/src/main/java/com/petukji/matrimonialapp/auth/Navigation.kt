package com.petukji.matrimonialapp.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.petukji.matrimonialapp.Views.RequestReceivedScreen
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel
import com.petukji.matrimonialapp.auth.presentation.ForWhomScreen
import com.petukji.matrimonialapp.auth.presentation.PersonalDetailPreview
import com.petukji.matrimonialapp.auth.presentation.PersonalDetailsForm
import com.petukji.matrimonialapp.auth.presentation.QualificationScreen
import com.petukji.matrimonialapp.bottom_nav.domain.HomeViewModel
import com.petukji.matrimonialapp.bottom_nav.nav_items.home.presentation.Home
import com.petukji.matrimonialapp.bottom_nav.nav_items.search.presentation.SearchScreen

@Composable
fun NavigationForAuth(
    navHostController: NavHostController,
    viewModel: PersonalDetailsViewModel
) {
    NavHost(navController = navHostController, startDestination = "for_whom") {
        composable("for_whom") {
            ForWhomScreen(navHostController, viewModel)
        }

        composable("personal_details") {
            PersonalDetailsForm(viewModel = viewModel, navController = navHostController)
        }

        composable("qualification") {
            QualificationScreen(viewModel = viewModel)
        }

    }
}