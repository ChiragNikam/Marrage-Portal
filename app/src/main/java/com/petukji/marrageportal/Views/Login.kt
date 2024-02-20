package com.petukji.marrageportal.Views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.marrageportal.AuthMenus
import com.petukji.marrageportal.ProfilePicCircular
import com.petukji.marrageportal.R
import com.petukji.marrageportal.myTextField

@Preview(showSystemUi = true)
@Composable
fun Login() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AuthMenus { // inside column scope
            MobileOTP()
        }
    }
}

@Composable
fun MobileOTP(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.one_step_ahead),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
            lineHeight = MaterialTheme.typography.headlineMedium.lineHeight
        )
        Spacer(modifier = Modifier.height(16.dp))
        // profile pic
        ProfilePicCircular()
        Spacer(modifier = Modifier.height(36.dp))

        myTextField(
            Modifier.padding(horizontal = 20.dp),
            hintText = "Mobile with Country Code",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(12.dp))
        myTextField(Modifier.padding(horizontal = 20.dp), hintText = "OTP",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Spacer(modifier = Modifier.height(38.dp))

        // Send OTP
        Button(shape = RoundedCornerShape(12.dp), onClick = { }) {
            Text(text = "Send OTP")
        }

        Spacer(modifier = Modifier.height(38.dp))
        Text(text = "00:59", fontSize = 24.sp, fontWeight = FontWeight(900))
        Spacer(modifier = Modifier.height(38.dp))

        Row(modifier = Modifier.padding(horizontal = 24.dp),) {
            Text(text = "\"", fontSize = 28.sp, fontWeight = FontWeight(900), color = MaterialTheme.colorScheme.primary)
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.soulmate_intro),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}