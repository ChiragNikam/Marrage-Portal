package com.petukji.matrimonialapp.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.data.api_data.RegistrationRequestData
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: PersonalDetailsViewModel,
    onRequestOtp: () -> Unit,
    onGoogleLogin: () -> Unit
) {
    val loginDetails = viewModel.personalDetails.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number Field
        OutlinedTextField(
            value = loginDetails.mobile, // Replace with state variable for phone number
            onValueChange = { viewModel.updatePhoneNo(it) },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onRequestOtp,
            enabled = true,
            modifier = Modifier.height(48.dp)
        ) {
            Text("Request OTP (30s)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // Google Login Button
//        OutlinedButton(
//            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
//            border = BorderStroke(0.dp, Color.Transparent),
//            modifier = modifier,
//            onClick = onClick
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.google_icon),
//                contentDescription = "Google Login"
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Login with Google")
//        }


        TextButton(
            onClick = { },
            border = BorderStroke(0.5.dp, Color(0xFF6750A3)),
            modifier = Modifier.height(48.dp)
        ) {
            Image(
                painter =painterResource(id = R.drawable.google_icon),
                contentDescription ="Google Login"
            )
            Text(
                text = "Login with Google",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        viewModel = PersonalDetailsViewModel(),
        onRequestOtp = { /*TODO*/ }) {

    }
}
