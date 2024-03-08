package com.petukji.marrageportal.bottom_nav.nav_items.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petukji.marrageportal.bottom_nav.domain.HomeViewModel
import com.petukji.marrageportal.bottom_nav.components.ProfileStatus
import com.petukji.marrageportal.R
import com.petukji.marrageportal.bottom_nav.components.UserProfile

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val paddingModifier = Modifier.padding(start = 20.dp, end = 20.dp)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // Profile
            UserProfile(name = "Rajat Kumar",
                id = "56125",
                image = R.drawable.tryimage,
                onImageClick = {})

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                modifier = modifier,
                text = "Let's AI Help You",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight(700)
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier = modifier,
                text = "Looking for your future partner\nOur AI will help you to find your perfect match.",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                textAlign = TextAlign.Center,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
            )

            Spacer(modifier = Modifier.height(18.dp))

            // status for profile, facebook, insta and whatsapp
            Box(
                modifier = Modifier
                    .padding(top = 8.dp, start = 20.dp)
                    .fillMaxWidth()
            ) {
                ProfileStatus(modifier = Modifier.padding(top = 8.dp, start = 8.dp))
                Icon(
                    modifier = Modifier
                        .padding(top = 0.dp)
                        .align(Alignment.TopStart),
                    painter = painterResource(id = R.drawable.icon_profile_status),
                    contentDescription = "profile status"
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Home(viewModel = HomeViewModel())
}


@Preview
@Composable
fun UserProfilePreview() {
    UserProfile(
        name = "Rajat Kumar",
        id = "56125",
        image = R.drawable.tryimage,
        onImageClick = {}
    )
}

