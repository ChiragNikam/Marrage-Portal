package com.petukji.marrageportal.member_info.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.petukji.marrageportal.member_info.domain.MemberInfoViewModel
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class MemberCompleteInfoActivity : ComponentActivity() {
    private lateinit var key : String

    private val viewModel by lazy { ViewModelProvider(this)[MemberInfoViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        key = Intent().getStringExtra("key").toString()
        super.onCreate(savedInstanceState)
        setContent {
            MarriagePortalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MemberCompleteInfo(viewModel = viewModel)
                }
            }
        }
    }
}
