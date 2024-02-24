package com.petukji.marrageportal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import com.petukji.marrageportal.Views.RequestReceivedScreen
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class RequestRecevedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarriagePortalTheme {
                // A surface container using the 'background' color from the theme
                RequestReceivedScreen(modifier = Modifier.verticalScroll(rememberScrollState()))
            }
        }
    }
}
