package com.petukji.matrimonialapp.member_info.presentation

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.petukji.matrimonialapp.bottom_nav.components.BlueTick
import com.petukji.matrimonialapp.bottom_nav.components.GreenTick
import com.petukji.matrimonialapp.bottom_nav.data.api_data.UserProfile
import com.petukji.matrimonialapp.member_info.domain.MemberInfoViewModel

@Composable
fun MemberCompleteInfo(modifier: Modifier = Modifier, viewModel: MemberInfoViewModel) {

    val selectedMenu by viewModel.infoMenusState.collectAsState()
    val userProfileData by viewModel.userProfileData.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {

        GirlImageAndInfoView(profileData = userProfileData)

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
                    title = "Personal",
                    selectedState = selectedMenu == 0
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
                    title = "Family",
                    selectedState = selectedMenu == 1
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
                    title = "Community",
                    selectedState = selectedMenu == 2
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
                    title = "Profession",
                    selectedState = selectedMenu == 3
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
                    title = "Habits",
                    selectedState = selectedMenu == 4
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                when (selectedMenu) {
                    0 -> ProfileInfoPersonal(userProfileData)
                    1 -> ProfileInfoFamily(userProfileData)
                    2 -> ProfileInfoCommunity(userProfileData)
                    3 -> ProfileInfoProfession(userProfileData)
                    4 -> ProfileInfoHabit(userProfileData)
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
    title: String,
    selectedState: Boolean = false
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
            tint = if(selectedState) Color.Blue.copy(0.5f) else MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = title,
            fontWeight = FontWeight(700),
            color = if(selectedState) Color.Blue.copy(0.5f) else MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun GirlImageAndInfoView(modifier: Modifier = Modifier, profileData: UserProfile) {

    val painter = rememberImagePainter(data = profileData.profileImagePath, builder = { crossfade(true) })

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
            painter = painter,
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
                        text = "${profileData.firstName + " " + profileData.lastName}, ${profileData.age} | ${profileData.bodyType}",
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
                            text = "${profileData.permanentCity}, ${profileData.permanentState}, ${profileData.permanentCountry}",
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
fun ProfileInfoPersonal(personalData: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 12.dp)

    ) {
        Text(text = "${personalData.occupation}| ${personalData.religion} |${personalData.zodiac} | ${personalData.nationality}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp, text = "Personality Details")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "${personalData.bodyType} |${personalData.bodyColor} ")
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp,text = "Introduction")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.longDescription)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp,text = "Partner Preference")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text =personalData.marriagePreference )
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp,text ="Qualification")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text =personalData.highestQualification)
    }
}

@Preview
@Composable
fun PreviewProfileInfoPersonal() {
    ProfileInfoPersonal(personalData = UserProfile())
}

@Composable
fun ProfileInfoFamily(personalData: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 12.dp)

    ){
        val fontStyle = MaterialTheme.typography.labelLarge.fontStyle
        Text(modifier = Modifier, fontStyle = fontStyle, text = "Total Family Members")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.totalFamily)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontStyle = fontStyle, text = "Is Joint Family")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.isJointFamily)
        Text(modifier = Modifier, fontStyle = fontStyle, text = "Total Members live together")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.memberLiveTogether)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontStyle = fontStyle, text = "Living with Family ?")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.areYouLivingWithFamily)
        Text(modifier = Modifier, fontStyle = fontStyle, text = "Address")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.permanentCity)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.permanentState)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.permanentCountry)
    }

}

@Composable
fun ProfileInfoCommunity(personalData: UserProfile) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 12.dp)

    ){
        Text(modifier = Modifier, fontSize = 12.sp, text = "Religion")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.religion)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp, text = "Religion")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.religion)

    }

}

@Composable
fun ProfileInfoProfession(personalData: UserProfile) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 12.dp)

    ){
        Text(modifier = Modifier, fontSize = 12.sp, text = "Company")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.companyName)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp, text = "CompanyAddress")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.companyAddress)

    }

}

@Composable
fun ProfileInfoHabit(personalData: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 12.dp)

    ){
        Text(modifier = Modifier, fontSize = 12.sp, text = "Interests")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.interests)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier, fontSize = 12.sp, text = "HabitInformation")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = personalData.badHabit)

    }
}

@Preview
@Composable
fun ProfileMenuPreview() {
    ProfileMenuItem(icon = Icons.Filled.Person, title = "Profile")
}