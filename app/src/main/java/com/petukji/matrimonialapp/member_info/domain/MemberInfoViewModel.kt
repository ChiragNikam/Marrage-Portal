package com.petukji.matrimonialapp.member_info.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfile
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MemberInfoViewModel: ViewModel() {
    //key of the selected profile
    private val _userProfileKey = MutableStateFlow("")
    val userProfileKey get() = _userProfileKey

    // store the selected state of the info-menus
    private val _infoMenusState = MutableStateFlow(0)
    val infoMenusState get() = _infoMenusState

    //personalDetails
    private val _userProfileData = MutableStateFlow(UserProfile())
    val userProfileData get() = _userProfileData

    fun updateUserProfileKey(key: String) {
        _userProfileKey.value = key
    }

    fun updateInfoMenusState(value: Int) {
        _infoMenusState.value = value
    }

    fun getSelectedUserProfile(data: UserProfileRequest) {
        viewModelScope.launch {
            val user=Users()
            val userProfileResponse = async { user.service.getSingleUserData(data) }

            val finalUserProfile = userProfileResponse.await()
            try {
                if (finalUserProfile.isSuccessful){
                    if (finalUserProfile.body() != null){
                        _userProfileData.value = finalUserProfile.body()!!
                    }
                    Log.d("user_profileData",_userProfileData.value.toString())
                } else{
                    Log.e("user_profileData", finalUserProfile.errorBody().toString())
                }
            } catch (e: Exception){
                Log.e("user_profile", e.message.toString())
            }
        }
    }
}