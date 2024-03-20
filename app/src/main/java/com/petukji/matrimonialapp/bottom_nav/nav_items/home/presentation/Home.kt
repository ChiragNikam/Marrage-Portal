package com.petukji.matrimonialapp.bottom_nav.nav_items.home.presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.bottom_nav.domain.HomeViewModel
import com.petukji.matrimonialapp.bottom_nav.components.AvailableGirlsVerticalGrid
import com.petukji.matrimonialapp.bottom_nav.components.UserProfile
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.common.GridContentBeforeLoading
import com.petukji.matrimonialapp.common.ProfileContentBeforeLoading
import com.petukji.matrimonialapp.common.ShimmerItem
import com.petukji.matrimonialapp.common.shimmerEffect

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navController: NavHostController
) {

    val isUserProfileLoading by viewModel.isUserProfileLoading.collectAsState()
    val isUserPreferenceLoading by viewModel.isUserPreferenceLoading.collectAsState()

    val userPreferenceData by viewModel.userPreferenceData.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            val userProfile by viewModel.userProfile.collectAsState()
            // Profile
            Row(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Align items vertically in the row
            ) {
                ShimmerItem(
                    isLoading = isUserProfileLoading,
                    contentBeforeLoading = {
                        ProfileContentBeforeLoading()
                    },
                    contentAfterLoading = {
                        UserProfile(
                            name = "${userProfile.firstName} ${userProfile.lastName}",
                            id = userProfile.userID,
                            imageUrl = userProfile.profileImagePath
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier) {
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



            Column(
                modifier = Modifier
                    .background(color = Color(0xFFFFC0CB))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navController.navigate("search")
                        },
                        modifier = Modifier
                            .size(48.dp),
                        enabled = !isUserPreferenceLoading
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_filter),
                            contentDescription = "Search"
                        )
                    }
                }
                ShimmerItem(
                    isLoading = isUserPreferenceLoading,
                    contentBeforeLoading = {
                        GridContentBeforeLoading()
                    },
                    contentAfterLoading = {
                        AvailableGirlsVerticalGrid(
                            gridItems = userPreferenceData,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp
                            )
                        )
                    })
            }
        }
    }


}

@Preview
@Composable
fun HomeScreenPreview() {
    Home(viewModel = HomeViewModel(), navController = rememberNavController())
}



