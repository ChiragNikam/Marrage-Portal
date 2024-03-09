package com.petukji.marrageportal.member_info.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.petukji.marrageportal.bottom_nav.data.api_data.UserProfileRequest
import com.petukji.marrageportal.member_info.domain.MemberInfoViewModel
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class MemberCompleteInfoActivity : ComponentActivity() {
    private lateinit var key : String

    private val viewModel by lazy { ViewModelProvider(this)[MemberInfoViewModel::class.java] }

    override fun onStart() {
        super.onStart()

        viewModel.getSelectedUserProfile(UserProfileRequest(mobileKey = key))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        key = intent.getStringExtra("key").toString()
        viewModel.updateUserProfileKey(key)
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