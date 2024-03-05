package com.petukji.marrageportal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.petukji.marrageportal.Views.Login
import com.petukji.marrageportal.bottom_nav.presentation.HomeActivity
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarriagePortalTheme {
                // A surface container using the 'background' color from the theme
                    Login{
                        startActivity(Intent(this, HomeActivity::class.java)).apply {
                            finish()
                        }
                    }
            }
        }
    }
}
