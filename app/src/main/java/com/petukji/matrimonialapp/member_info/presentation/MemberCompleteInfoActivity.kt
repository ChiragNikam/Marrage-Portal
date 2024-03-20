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
import androidx.lifecycle.lifecycleScope
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.member_info.domain.MemberInfoViewModel
import com.petukji.matrimonialapp.ui.theme.MarriagePortalTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MemberCompleteInfoActivity : ComponentActivity() {
    private lateinit var key: String

    private val viewModel by lazy { ViewModelProvider(this)[MemberInfoViewModel::class.java] }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            // get profile of the selected user
            viewModel.getSelectedUserProfile(UserProfileRequest(mobileKey = key))
            // set viewed profile to api
//            viewModel.profileViewedRequest(UserProfileRequest(mobileKey = "11234567894"))

            viewModel.getShortListedProfilesByMe(userMobile = "11234567894")
            viewModel.isCurrentProfileShortListed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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