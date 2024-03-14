package com.petukji.marrageportal.bottom_nav.nav_items.home.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.petukji.marrageportal.bottom_nav.domain.HomeViewModel
import com.petukji.marrageportal.bottom_nav.components.ProfileStatus
import com.petukji.marrageportal.R
import com.petukji.marrageportal.bottom_nav.components.AvailableGirlsVerticalGrid
import com.petukji.marrageportal.bottom_nav.components.UserProfile

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel,navController:NavHostController) {

    val userPreferenceData by viewModel.userPreferenceData.collectAsState()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(26.dp))

            val userProfile by viewModel.userProfile.collectAsState()
            // Profile
            UserProfile(name = "${userProfile.firstName} ${userProfile.lastName}",
                id = userProfile.userID,
                imageUrl = userProfile.profileImagePath,
                onImageClick = {})

            Spacer(modifier = Modifier.height(20.dp))
            Row (modifier=Modifier){
                Text(
                    modifier = modifier,
                    text = "Let's AI Help You",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight(400)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = modifier,
                text = "Looking for your future partner\nOur AI will help you to find your perfect match.",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                textAlign = TextAlign.Center,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
            )
            Spacer(modifier = Modifier.height(8.dp))



            Column(modifier= Modifier
                .background(color = Color(0xFFFFC0CB))
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier=Modifier
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.filled_search),
                        contentDescription = "Search",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(30.dp)
                            .clickable {
                                navController.navigate("search")
                            }
                    )
                }
                AvailableGirlsVerticalGrid(
                    gridItems = userPreferenceData,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp
                    ))
            }
        }
    }



}

@Preview
@Composable
fun HomeScreenPreview() {
    Home(viewModel = HomeViewModel(), navController = rememberNavController())
}



