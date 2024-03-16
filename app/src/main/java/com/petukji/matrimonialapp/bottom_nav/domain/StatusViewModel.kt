package com.petukji.matrimonialapp.bottom_nav.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.SingleUserPreference
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import com.petukji.matrimonialapp.member_info.data.api_data.ShortlistReadRequest
import com.petukji.matrimonialapp.member_info.data.api_data.ShortlistedProfile
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatusViewModel: ViewModel() {

    // shortlisted profiles by me
    private val _shortlistedProfiles = MutableStateFlow(mutableListOf<ShortlistedProfile>())
    val shortlistedProfiles get() = _shortlistedProfiles

    // profile data for shortlisted profiles
    private val _profilesData = MutableStateFlow(mutableListOf<SingleUserPreference>())
    val profilesData get() = _profilesData

    // selected statue for tab row
    private val _tabSelectedState = MutableStateFlow(0)
    val tabSelectedState get() = _tabSelectedState

    val user = Users()

    fun updateTabSelectedState(state: Int){
        _tabSelectedState.value = state
    }

    suspend fun getShortListedProfilesByMe(userMobile: String) {
        viewModelScope.launch {
            try {
                val shortListedProfilesRes = async {
                    user.service.getShortListedProfile(
                        ShortlistReadRequest(
                            queryFor = "byme",
                            userMobile = userMobile,
                            topQuery = "detail"
                        )
                    )
                }

                val finalShortListedProfileRes = shortListedProfilesRes.await()

                if (finalShortListedProfileRes.isSuccessful) {
                    if (finalShortListedProfileRes.body() != null) {
                        for (profile in finalShortListedProfileRes.body()!!.data){
                            _shortlistedProfiles.value.add(profile)
                        }
                    }
                    Log.d("shortlist", _shortlistedProfiles.value.toString())

                    getDetailsOfShortlistedProfiles()

                } else {
                    Log.e("shortlisted_profiles", "something went wrong while getting profile")
                }
            } catch (e: Exception) {
                Log.e("shortlist", e.message.toString())
            }
        }
    }

    fun getDetailsOfShortlistedProfiles(){
        viewModelScope.launch {
            for (profile in shortlistedProfiles.value){
                try {
                    // calling
                    val userProfileResponse = async { user.service.getSingleUserPreference(UserProfileRequest(mobileKey = profile.to)) }

                    val finalUserProfile = userProfileResponse.await()
                    if (finalUserProfile.isSuccessful) {
                        if (finalUserProfile.body() != null) {
                            _profilesData.value.add(finalUserProfile.body()!!.userPreference)
                            Log.d("shortlisted", finalUserProfile.body().toString())
                        }
                    } else {
                        Log.e("user_profileData", finalUserProfile.errorBody().toString())
                    }
                } catch (e: Exception) {
                    Log.e("user_profile", e.message.toString())
                }
            }
        }
    }
}