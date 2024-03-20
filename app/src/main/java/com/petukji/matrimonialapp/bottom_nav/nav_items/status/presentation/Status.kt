package com.petukji.matrimonialapp.bottom_nav.nav_items.status.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.bottom_nav.components.AvailableGirlsVerticalGrid
import com.petukji.matrimonialapp.bottom_nav.nav_items.status.domain.StatusViewModel
import com.petukji.matrimonialapp.common.GridContentBeforeLoading
import com.petukji.matrimonialapp.common.ShimmerItem

@Composable
fun RequestReceivedScreen(modifier: Modifier = Modifier, viewModel: StatusViewModel) {

    val tabSelectedState by viewModel.tabSelectedState.collectAsState()

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFF7FFFD4))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SettingRequestRecived(modifier = Modifier.weight(1f), tabSelectedState)
                Icon(
                    modifier = Modifier.weight(0.2f),
                    painter = painterResource(id = R.drawable.ic_gridicon),
                    tint = Color.Blue,
                    contentDescription = "Grid Icon"
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (tabSelectedState) {
                        0 -> ProfilesViewed(viewModel)
                        1 -> RequestReceived()
                        2 -> ShortlistedProfiles(viewModel)
                    }
                }
                //
                TabRowOptions(
                    modifier = Modifier
                        .background(
                            Color.White,
                            RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                        ),
                    viewModel
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ProfilesViewed(viewModel: StatusViewModel) {
    val profiles by viewModel.profilesDataViewedProfiles.collectAsState()
    val isProfileLoading by viewModel.isShortlistedProfilesLoading.collectAsState()

    ShimmerItem(isLoading = isProfileLoading,
        contentBeforeLoading = {
            GridContentBeforeLoading()
        },
        contentAfterLoading = {
            Log.d("profiles_viewed", "size: ${profiles.size}, $profiles")
            AvailableGirlsVerticalGrid(gridItems = profiles)
        })
}

@Composable
fun ShortlistedProfiles(viewModel: StatusViewModel) {
    val profiles by viewModel.profilesData.collectAsState()
    val isProfileLoading by viewModel.isShortlistedProfilesLoading.collectAsState()

    ShimmerItem(isLoading = isProfileLoading,
        contentBeforeLoading = {
            GridContentBeforeLoading()
        },
        contentAfterLoading = {
            AvailableGirlsVerticalGrid(gridItems = profiles)
        })
}

@Preview
@Composable
fun RequestGirlInfoView(modifier: Modifier = Modifier) {
    // Name, Place, Short Description, Shortlisted Date
    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Priya Jaiswal, 26",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight(700),
            color = Color.Blue
        )
        Text(
            text = "Delhi, India",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight(500),
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(
                text = "Confident Girl, Post Graduate in Computer, not working,Seeking a real man who respect women and living with joint " +
                        "family. CA/CS will have priotity",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Shortlisted Date: 25 July 2023")

                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.chain),
                    contentDescription = "Pin Icon"
                )
            }
        }

    }
}

@Composable
fun SettingRequestRecived(modifier: Modifier = Modifier, tabSelectedState: Int) {

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    )
    {

        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.onPrimary,
                    RoundedCornerShape(
                        topStart = 18.dp,
                        topEnd = 18.dp,
                        bottomStart = 18.dp,
                        bottomEnd = 18.dp
                    )
                )
                .padding(start = 4.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
        ) {

            Box(
                modifier = modifier.background(color = Color.Yellow, shape = CircleShape)
            ) {

                Icon(
                    modifier = Modifier
                        .background(Color(0xFFFFA500), CircleShape)
                        .size(36.dp)
                        .padding(3.dp),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Icon",
                )
            }
            Spacer(modifier = modifier.width(4.dp))

            Row(modifier = modifier.padding(start = 40.dp, top = 8.dp)) {
                Text(
                    text = when (tabSelectedState) {
                        0 -> "Profile Viewed"
                        1 -> "Request Received"
                        2 -> "Shortlisted Profiles"
                        else -> " "
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }

        }
    }
}

@Composable
fun RequestReceived() {
    RequestGirlInfoView(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(color = Color(0xFFDBF9DB), RoundedCornerShape(18.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
    Spacer(modifier = Modifier.height(20.dp))
    Box(modifier = Modifier.padding(horizontal = 24.dp)) {
//                        ImageViewWithGreenBlueTick(
//                            tickModifier = Modifier.align(Alignment.TopEnd),
//                            imageHeight = 440.dp,
//
//                            )
    }
}

// tab options row for Request Received Screen
@Composable
fun TabRowOptions(modifier: Modifier = Modifier, viewModel: StatusViewModel) {
    val tabSelected by viewModel.tabSelectedState.collectAsState()
    Column {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(30.dp)) {
            IconButton(onClick = {
                viewModel.updateTabSelectedState(0)
            }) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "profile viewed",
                    tint = if (tabSelected == 0) MaterialTheme.colorScheme.primary else Color.Black
                )
            }
            IconButton(onClick = {
                viewModel.updateTabSelectedState(1)
            }) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "accepted request",
                    tint = if (tabSelected == 1) MaterialTheme.colorScheme.primary else Color.Black
                )
            }
            IconButton(onClick = {
                viewModel.updateTabSelectedState(2)
            }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "shortlisted",
                    tint = if (tabSelected == 2) MaterialTheme.colorScheme.primary else Color.Black
                )
            }
            IconButton(onClick = {
                viewModel.updateTabSelectedState(3)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_info),
                    contentDescription = "info",
                    tint = if (tabSelected == 3) MaterialTheme.colorScheme.primary else Color.Black
                )
            }
            IconButton(onClick = {
                viewModel.updateTabSelectedState(4)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_document),
                    contentDescription = "info",
                    tint = if (tabSelected == 4) MaterialTheme.colorScheme.primary else Color.Black
                )
            }
            IconButton(onClick = {
                viewModel.updateTabSelectedState(5)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.filled_profile),
                    contentDescription = "info"
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "settings")
        }
    }
}

@Preview
@Composable
fun TabRowOptionsPreview() {
    TabRowOptions(
        modifier = Modifier.background(
            Color.White,
            RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
        ),
        viewModel = viewModel()
    )
}

@Preview
@Composable
fun RequestCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.my_profile_photo),
            contentDescription = "Profile Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Priya Jaiswal, 26",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Delhi, India",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Confident Girl, Post Graduate in Computer, not working," +
                    "Seeking a real man who respect women and living with joint" +
                    " family. CA/CS will have priotity",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            fontStyle = null,
            fontFamily = FontFamily.Default
        )


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Shortlisted Date: 25 July 2023",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}