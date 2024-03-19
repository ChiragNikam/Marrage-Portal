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
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    val verificationID = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val loginDetails = viewModel.personalDetails.value

    var otpField by remember {
        mutableStateOf("")
    }

    val message = remember {
        mutableStateOf("")
    }

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
            value = otpField,
            onValueChange = { otpField = it },
            label = { Text("OTP") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextButton(
            modifier = Modifier
                .align(Alignment.End)
                .height(48.dp),
            onClick = {
                // on below line we are validating user inputs
                if (TextUtils.isEmpty(loginDetails.mobile)) {
                    Toast.makeText(context, "Please enter phone number..", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    val number = "+91${loginDetails.mobile}"
                    // on below line calling method to generate verification code.
                    sendVerificationCode(number, auth, context as Activity, callbacks)
                }
            },
            enabled = true
        ) {
            Text("Request OTP (30s)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
//            // on below line we are validating
//            // user input parameters.
//            if (TextUtils.isEmpty(otpField)) {
//                // displaying toast message on below line.
//                Toast.makeText(context, "Please enter otp..", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            else {
//                // on below line generating phone credentials.
//                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
//                    verificationID.value, otpField
//                )
//                // on below line signing within credentials.
//                signInWithPhoneAuthCredential(
//                    credential,
//                    auth,
//                    context as Activity,
//                    context,
//                    message
//                )
//            }
            navController.navigate("personal_details")

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

    // on below line creating callback
    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            // on below line updating message
            // and displaying toast message
            message.value = "Verification successful"
            Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            navController.navigate("personal_details")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            // on below line displaying error as toast message.
            message.value = "Fail to verify user : \n" + p0.message
            Toast.makeText(context, "Verification failed..", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            // this method is called when code is send
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
        }
    }
}

// sign in with phone credentuals.
private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    activity: Activity,
    context: Context,
    message: MutableState<String>
) {
    // on below line signing with credentials.
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            // displaying toast message when
            // verification is successful
            if (task.isSuccessful) {
                Log.d("verification", "task successfull")
                message.value = "Verification successful"
                Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            } else {
                // Sign in failed, display a message
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code
                    // entered was invalid
                    Log.d("auth", (task.exception as FirebaseAuthInvalidCredentialsException).message.toString())
                    Toast.makeText(
                        context,
                        "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}

// verification code to user phone number.
private fun sendVerificationCode(
    number: String,
    auth: FirebaseAuth,
    activity: Activity,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
    // on below line generating options for verification code
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number) // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(activity) // Activity (for callback binding)
        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
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
