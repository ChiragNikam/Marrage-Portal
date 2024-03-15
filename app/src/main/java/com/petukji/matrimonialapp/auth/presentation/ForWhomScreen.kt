package com.petukji.matrimonialapp.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.navigation.NavController
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.data.SelectionType
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel


@Composable
fun ForWhomScreen(navController: NavController, viewModel: PersonalDetailsViewModel) {
    Column(
        modifier = Modifier.padding(top = 210.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "For whom are you creating?",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Spacer(modifier = Modifier.height(24.dp))

        SelectionType.entries.forEach { groom ->
            GradientButton(
                text = groom.name
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.lightRed)
            ),
            onClick = {
                navController.navigate("personal_details")
            }
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun GradientButton(
    text: String,
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

        //should have no additional padding around it inside the button.
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
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

@Preview()
@Composable
fun GradientButtonPreview() {
    GradientButton(
        text = "Style: top Start"
    )
}