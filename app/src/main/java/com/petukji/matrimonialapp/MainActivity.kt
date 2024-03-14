package com.petukji.matrimonialapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.petukji.matrimonialapp.Views.Login
import com.petukji.matrimonialapp.bottom_nav.presentation.HomeActivity
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme

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
