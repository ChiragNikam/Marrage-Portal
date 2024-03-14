package com.petukji.matrimonialapp.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.data.SelectionType

@Composable
fun RegistrationScreen() {
    ForWhomScreen()
}

@Composable
fun ForWhomScreen() {
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
            onClick = { }
        ) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}