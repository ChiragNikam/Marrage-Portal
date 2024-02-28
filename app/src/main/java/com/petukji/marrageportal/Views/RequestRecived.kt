package com.petukji.marrageportal.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petukji.marrageportal.ImageViewWithGreenBlueTick
import com.petukji.marrageportal.R
import com.petukji.marrageportal.TabRowOptions


@Preview(showSystemUi = true)
@Composable
fun RequestReceivedScreen(modifier: Modifier = Modifier) {
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
                SettingRequestRecived(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.weight(0.2f),
                    painter = painterResource(id = R.drawable.ic_gridicon),
                    tint = Color.Blue,
                    contentDescription = "Grid Icon"
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RequestGirlInfoView(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .background(color = Color(0xFFDBF9DB), RoundedCornerShape(18.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                        ImageViewWithGreenBlueTick(
                            tickModifier = Modifier.align(Alignment.TopEnd),
                            imageHeight = 440.dp,

                            )
                    }
                }
                //
                TabRowOptions(
                    modifier = Modifier
                        .background(
                            Color.White,
                            RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
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
fun SettingRequestRecived(modifier: Modifier = Modifier) {

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
                    text = "Request Received(4)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }

        }
    }
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