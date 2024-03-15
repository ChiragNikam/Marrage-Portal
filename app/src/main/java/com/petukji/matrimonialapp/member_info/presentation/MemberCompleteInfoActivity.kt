package com.petukji.matrimonialapp.member_info.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.member_info.domain.MemberInfoViewModel
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MemberCompleteInfoActivity : ComponentActivity() {
    private lateinit var key : String

    private val viewModel by lazy { ViewModelProvider(this)[MemberInfoViewModel::class.java] }

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            viewModel.getSelectedUserProfile(UserProfileRequest(mobileKey = key))

            viewModel.profileViewedRequest(UserProfileRequest(mobileKey = "11234567894"))
        }
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