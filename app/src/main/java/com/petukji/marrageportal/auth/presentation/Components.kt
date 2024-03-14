package com.petukji.marrageportal.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.marrageportal.R

@Composable
fun GradientButton(
    text: String,
//    roundedCornerShape: RoundedCornerShape
) {
    val gradient = listOf(
        colorResource(id = R.color.lightGreen),
        colorResource(id = R.color.lightPink)
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 32.dp, end = 32.dp, top = 8.dp),
        onClick = {},

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .background(
//                    brush = Brush.horizontalGradient(colors = gradient),
//                    shape = roundedCornerShape
//                )
                .clip(shape = RoundedCornerShape(24.dp))
                .background(
                    brush = Brush.linearGradient(colors = gradient),
                    shape = RoundedCornerShape(24.dp)
                )
                .height(60.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun GradientButton2(text: String, onClick: () -> Unit) {

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            colorResource(id = R.color.lightGreen),
            colorResource(id = R.color.lightPink)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable(onClick = onClick)
            .clip(shape = RoundedCornerShape(24.dp))
            .background(
                brush = gradient
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}

@Preview
@Composable
fun GradientButton2Preview() {
    GradientButton2(text = "MySelf", onClick = { })
}

@Preview(showSystemUi = true)
@Composable
fun GradientButtonPreview() {
    GradientButton(
        text = "Style: top Start"
    )
}