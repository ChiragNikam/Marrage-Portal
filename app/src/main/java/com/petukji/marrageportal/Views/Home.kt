package com.petukji.marrageportal.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.petukji.marrageportal.ProfilePicCircular
import com.petukji.marrageportal.R

@Preview(showSystemUi = true)
@Composable
fun Home(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            ProfileView()

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Let's AI Help You",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight(700)
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Looking for your future partner\nOur AI will help you to find your perfect match.",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                textAlign = TextAlign.Center,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
            )
        }
    }
}

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
                Icon(
                    modifier = Modifier.background(Color.Green, shape = CircleShape),
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "green tick",
                    tint = Color.White
                )
                Icon(
                    modifier = Modifier.background(Color.Blue, shape = CircleShape),
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "green tick",
                    tint = Color.White
                )
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