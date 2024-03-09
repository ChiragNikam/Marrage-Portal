package com.petukji.marrageportal.member_info.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petukji.marrageportal.bottom_nav.components.BlueTick
import com.petukji.marrageportal.bottom_nav.components.GreenTick
import com.petukji.marrageportal.R
import com.petukji.marrageportal.member_info.domain.MemberInfoViewModel

@Composable
fun MemberCompleteInfo(modifier: Modifier = Modifier, viewModel: MemberInfoViewModel) {

    val selectedMenu by viewModel.infoMenusState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {

        GirlImageAndInfoView()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.7f))
            Row(
                modifier = Modifier
                    .weight(1.3f)
                    .fillMaxHeight()
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Filled.Send,
                    contentDescription = "send interest",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Send Interest",
                    fontStyle = MaterialTheme.typography.labelLarge.fontStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "short list",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Short List",
                    fontStyle = MaterialTheme.typography.labelLarge.fontStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // menus of profile
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.38f)
                    .background(MaterialTheme.colorScheme.primary)

            ) {
                ProfileMenuItem(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            viewModel.updateInfoMenusState(0)
                        },
                    iconModifier = Modifier.size(35.dp),
                    icon = Icons.Filled.Person,
                    title = "Personal"
                )
                ProfileMenuItem(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            viewModel.updateInfoMenusState(1)
                        },
                    iconModifier = Modifier.size(35.dp),
                    icon = Icons.Filled.Face,
                    title = "Family"
                )
                ProfileMenuItem(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            viewModel.updateInfoMenusState(2)
                        },
                    iconModifier = Modifier.size(35.dp),
                    icon = Icons.Filled.Info,
                    title = "Community"
                )
                ProfileMenuItem(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            viewModel.updateInfoMenusState(3)
                        },
                    iconModifier = Modifier.size(35.dp),
                    icon = Icons.Filled.Notifications,
                    title = "Profession"
                )
                ProfileMenuItem(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            viewModel.updateInfoMenusState(4)
                        },
                    iconModifier = Modifier.size(35.dp),
                    icon = Icons.Filled.Call,
                    title = "Habits"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                when (selectedMenu) {
                    0 -> ProfileInfoPersonal()
                    1 -> ProfileInfoFamily()
                    2 -> ProfileInfoCommunity()
                    3 -> ProfileInfoProfession()
                    4 -> ProfileInfoHabit()
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    icon: ImageVector,
    title: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = icon,
            contentDescription = "personal",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = title,
            fontWeight = FontWeight(700),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun GirlImageAndInfoView(modifier: Modifier = Modifier) {
    val gradient = Brush.verticalGradient(
        listOf(Color.Transparent, MaterialTheme.colorScheme.primary),
        startY = 0.0f,
        endY = 300.0f
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sampleimage),
            contentScale = ContentScale.FillBounds,
            contentDescription = "girl photo",
        )

        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxWidth()
                .height(130.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.BottomStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    // name and height
                    Text(
                        text = "Richa Jain, 78 | 5' 6\"",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight(700)
                    )
                    // location
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "location",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Meerut, Uttar Pradesh, India",
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                GreenTick(Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(6.dp))
                BlueTick(Modifier.size(24.dp))

                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

// Profile menu items: Personal, Family, Community, Profession, Habit
@Composable
fun ProfileInfoPersonal() {

}

@Composable
fun ProfileInfoFamily() {

}

@Composable
fun ProfileInfoCommunity() {

}

@Composable
fun ProfileInfoProfession() {

}

@Composable
fun ProfileInfoHabit() {

}

@Preview
@Composable
fun ProfileMenuPreview() {
    ProfileMenuItem(icon = Icons.Filled.Person, title = "Profile")
}