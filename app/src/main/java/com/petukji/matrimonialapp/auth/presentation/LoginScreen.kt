package com.petukji.matrimonialapp.auth.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.petukji.matrimonialapp.R
import com.petukji.matrimonialapp.auth.data.api_data.RegistrationRequestData
import com.petukji.matrimonialapp.auth.domain.PersonalDetailsViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: PersonalDetailsViewModel,
    onRequestOtp: () -> Unit,
    onGoogleLogin: () -> Unit
) {
    // Initialize Firebase Auth
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser  // for current user

    val context = LocalContext.current

    val loginDetails = viewModel.personalDetails.value

    var otpField by remember {
        mutableStateOf("")
    }

    val message = remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

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

        // OTP text field
        OutlinedTextField(
            value = otpField,
            onValueChange = { otpField = it },
            label = { Text("OTP") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // generate OTP
        TextButton(
            modifier = Modifier
                .align(Alignment.End)
                .height(48.dp),
            onClick = {
                requestOtp("+91${loginDetails.mobile}", context as Activity, viewModel)
            },
            enabled = true
        ) {
            Text("Request OTP (30s)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // validate using OTP and login
        Button(onClick = {
            if(otpField.isEmpty()){
                Toast.makeText(context, "Please enter the Otp ", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.verifyOtp(loginDetails.mobile, otpField) { success ->
                    if (success) {
                        Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate("personal_details")
                    } else {
                        coroutineScope.launch {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { },
            border = BorderStroke(0.5.dp, Color(0xFF6750A3)),
            modifier = Modifier.height(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google Login"
            )
            Text(
                text = "Login with Google",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )
        }
    }

}

fun requestOtp(phoneNumber: String, activity: Activity, viewModel: PersonalDetailsViewModel) {
    val firebaseAuth = FirebaseAuth.getInstance()

    val options = PhoneAuthOptions.newBuilder(firebaseAuth)
        .setPhoneNumber(phoneNumber)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity) // Pass activity if you're using FirebaseUI Auth
        .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                // Here, you can handle the verification completed event.
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                // Depending on the error, you can use some other response codes to
                // show appropriate error message to the user.
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number,
                // we now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                // Save the verification ID somewhere for verification later.
                viewModel._verificationId.value = verificationId
            }
        })
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
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
