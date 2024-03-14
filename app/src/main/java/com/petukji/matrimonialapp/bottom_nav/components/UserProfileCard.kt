package com.petukji.matrimonialapp.bottom_nav.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import coil.compose.rememberImagePainter
import com.petukji.matrimonialapp.R


@Composable
fun UserProfile(
    modifier: Modifier = Modifier,
    name: String,
    id: String,
    imageUrl: String,
    onImageClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically // Align items vertically in the row
    ) {
        Column(
            modifier = modifier
                .weight(1f) // Take up available horizontal space
        ) {
            Text(
                text = "Hi! $name",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight(700)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "ID: $id",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            BiodataDocuments()
        }

        Spacer(modifier = Modifier.width(16.dp)) // Add space between user details and image

        UserImage(
            imageUrl = imageUrl,
            onImageClick = onImageClick,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .clickable { onImageClick() }
        )
    }
}

@Composable
fun UserImage(
    imageUrl: String,
    onImageClick: () -> Unit,
    modifier: Modifier
) {
    val painter = rememberImagePainter(data = imageUrl, builder = { crossfade(true) })

    Box {
        Box(modifier = modifier
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
        IconButton(
            onClick = { onImageClick() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 6.dp, end = 8.dp)
                .size(28.dp)
        )
        {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Change image",
                tint = MaterialTheme.colorScheme.primary
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
        modifier = modifier
            .fillMaxWidth(1f)

    ) {

        Icon(
            painter = painterResource(R.drawable.icon_profile_status),
            contentDescription = "Bio data",
            Modifier.size(18.dp),
            tint = Color.Red

        )
        Spacer(modifier = Modifier.width(2.dp))

        GreenTick(modifier = Modifier.size(18.dp))

        Spacer(modifier = Modifier.width(2.dp))

        BlueTick(modifier = Modifier.size(18.dp))

    }
}