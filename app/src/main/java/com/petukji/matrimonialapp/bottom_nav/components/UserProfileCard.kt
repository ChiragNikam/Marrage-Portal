package com.petukji.matrimonialapp.bottom_nav.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.petukji.matrimonialapp.R


@Composable
fun UserProfile(
    modifier: Modifier = Modifier,
    name: String,
    id: String,
    imageUrl: String
) {
    UserImage(
        imageUrl = imageUrl,
        modifier = Modifier
            .size(52.dp)
            .clip(CircleShape)
    )

    Spacer(modifier = Modifier.width(16.dp)) // Add space between user details and image

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Hi! $name",
                fontSize = 22.sp,
                fontWeight = FontWeight(700)
            )

            Text(
                text = "ID: $id",
                fontSize = 10.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        BiodataDocuments()
    }
}

@Composable
fun UserImage(
    imageUrl: String,
    modifier: Modifier
) {
    val painter = rememberImagePainter(data = imageUrl, builder = { crossfade(true) })

    Box {
        Box(
            modifier = modifier
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = painter, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}


@Composable
fun BiodataDocuments(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = modifier
    ) {

        Icon(
            painter = painterResource(R.drawable.icon_profile_status),
            contentDescription = "Bio data",
            Modifier.size(22.dp),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(2.dp))

        GreenTick(modifier = Modifier.size(22.dp))

        Spacer(modifier = Modifier.width(2.dp))

        BlueTick(modifier = Modifier.size(22.dp))

    }
}