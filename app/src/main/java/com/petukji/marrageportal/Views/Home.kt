package com.petukji.marrageportal.Views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.DateRange
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.marrageportal.AvailableGirlsVerticalGrid
import com.petukji.marrageportal.BlueTick
import com.petukji.marrageportal.GreenTick
import com.petukji.marrageportal.HomeViewModel
import com.petukji.marrageportal.ProfileStatus
import com.petukji.marrageportal.R

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val paddingModifier = Modifier.padding(start = 20.dp, end = 20.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // Profile
            ProfileView(modifier = paddingModifier)

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

            val showGirls by viewModel.showGirls.collectAsState()

            Spacer(modifier = Modifier.height(18.dp))
//            if (showGirls) {
            AnimatedVisibility(visible = showGirls,
                    enter = slideInVertically(
                        // Enters by sliding down from offset -fullHeight to 0.
                        initialOffsetY = { fullHeight -> fullHeight }
                    ),
                    exit = slideOutVertically(
                        // Exits by sliding up from offset 0 to -fullHeight.
                        targetOffsetY = { fullHeight -> fullHeight }
                    )) {
                    Surface( color = MaterialTheme.colorScheme.primaryContainer) {
                        Column {
                            IconButton(modifier = Modifier.align(Alignment.End), onClick = { viewModel.updateShowGirls(false) }) {
                                Icon(imageVector = Icons.Outlined.Clear, contentDescription = "cancel")
                            }
                            AvailableGirlsVerticalGrid(modifier = Modifier.padding(horizontal = 20.dp))
                        }
                    }
                }
//            } else {
                // status for profile, facebook, insta and whatsapp
                AnimatedVisibility(visible = !showGirls,
                        enter = slideInVertically(
                        // Enters by sliding down from offset -fullHeight to 0.
                        initialOffsetY = { fullHeight -> -fullHeight }
                        ),
                    exit = slideOutVertically(
                        // Exits by sliding up from offset 0 to -fullHeight.
                        targetOffsetY = { fullHeight -> -fullHeight }
                    )) {
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
    }
//}

@Preview
@Composable
fun ProfileView(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Hi! Chirag Nikam",
                fontSize = 18.sp,
                fontWeight = FontWeight(800)
            )
            Text(
                text = " ID: 12334234587",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Icon(imageVector = Icons.Outlined.DateRange, contentDescription = "")

                GreenTick()

                BlueTick()
            }
        }
        Image(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inversePrimary, shape = CircleShape)
                .size(150.dp)
                .padding(5.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.my_profile_photo),
            contentScale = ContentScale.Fit,
            contentDescription = "profile pic"
        )
    }
}