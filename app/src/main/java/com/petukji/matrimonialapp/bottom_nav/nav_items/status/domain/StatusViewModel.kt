package com.petukji.matrimonialapp.bottom_nav.nav_items.status.domain

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
import org.json.JSONException
import org.json.JSONObject

class StatusViewModel : ViewModel() {

    // shortlisted profiles by me
    private val _shortlistedProfiles = MutableStateFlow(mutableListOf<ShortlistedProfile>())
    val shortlistedProfiles get() = _shortlistedProfiles

    private val _isShortlistedProfilesLoading = MutableStateFlow(true)
    val isShortlistedProfilesLoading get() = _isShortlistedProfilesLoading

    // profile data for shortlisted profiles
    private val _profilesData = MutableStateFlow(mutableListOf<SingleUserPreference>())
    val profilesData get() = _profilesData

    // viewed profiles list by me
    private val _viewedProfiles = MutableStateFlow(mutableListOf<ShortlistedProfile>())
    val viewedProfiles get() = _viewedProfiles

    // profile data for viewed profiles
    private val _profilesDataViewedProfiles =
        MutableStateFlow(mutableListOf<SingleUserPreference>())
    val profilesDataViewedProfiles get() = _profilesDataViewedProfiles

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing get() = _isRefreshing

    // selected statue for tab row
    private val _tabSelectedState = MutableStateFlow(0)
    val tabSelectedState get() = _tabSelectedState

    val user = Users()

    fun updateTabSelectedState(state: Int) {
        _tabSelectedState.value = state
    }

    fun updateRefreshStatus(status: Boolean){
        _isRefreshing.value = status
    }

    suspend fun refresh(){
        viewModelScope.launch {
            // clear the data from the list
            _shortlistedProfiles.value.clear()
            _isShortlistedProfilesLoading.value = true
            _profilesData.value.clear()
            _viewedProfiles.value.clear()
            _profilesDataViewedProfiles.value.clear()

            async {
                getShortListedProfilesByMe("11234567894")
                getProfilesViewedByMe("11234567894")
            }

            _isRefreshing.value = false
        }
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
                        for (profile in finalShortListedProfileRes.body()!!.data) {
                            _shortlistedProfiles.value.add(profile)
                        }
                    }
                    Log.d("shortlist", _shortlistedProfiles.value.toString())

                    getDetailsOfShortlistedProfiles(shortlistedProfiles.value)

                } else {
                    Log.e("shortlisted_profiles", "something went wrong while getting profile")
                }
            } catch (e: Exception) {
                Log.e("shortlist", e.message.toString())
            }
        }
    }

    private fun getDetailsOfShortlistedProfiles(
        profiles: MutableList<ShortlistedProfile>,
        type: String = "shortlisted"
    ) {
        viewModelScope.launch {
            val newDataList = mutableListOf<SingleUserPreference>() // Create a new list to store updated data

            for (profile in profiles) {
                try {
                    // calling
                    val userProfileResponse =
                        async { user.service.getSingleUserPreference(UserProfileRequest(mobileKey = profile.to)) }

                    val finalUserProfile = userProfileResponse.await()
                    if (finalUserProfile.isSuccessful) {
                        finalUserProfile.body()?.let { userPreference ->
                            newDataList.add(userPreference.userPreference) // Add the userPreference to the new list
                        }
                    } else {
                        Log.e("user_profileData", finalUserProfile.errorBody().toString())
                    }
                } catch (e: Exception) {
                    Log.e("user_profile", e.message.toString())
                }
                if (type == "shortlisted") {
                    _profilesData.value.addAll(newDataList) // Add all userPreferences to the existing list
                    _isShortlistedProfilesLoading.value = false
                } else if (type == "viewed_profiles") {
                    _profilesDataViewedProfiles.value = newDataList // Update the StateFlow with the new list
                    _isShortlistedProfilesLoading.value = false
                }
            }
        }
    }

    suspend fun getProfilesViewedByMe(userMobile: String) {
        viewModelScope.launch {
            try {
                // calling
                val viewedProfilesResponse = async {
                    user.service.getViewedProfiles(
                        ShortlistReadRequest(
                            logType = "view",
                            queryFor = "byme",
                            userMobile = userMobile,
                            topQuery = "detail"
                        )
                    )
                }

                val finalViewedProfilesResp = viewedProfilesResponse.await()

                if (finalViewedProfilesResp.isSuccessful) {
                    _viewedProfiles.value = finalViewedProfilesResp.body()!!.data.toMutableList()
                    Log.d(
                        "viewed_profiles",
                        "size: ${viewedProfiles.value.size}, data: ${viewedProfiles.value}"
                    )
                    // get details of single user
                    getDetailsOfShortlistedProfiles(viewedProfiles.value, "viewed_profiles")
                } else {
                    val errorBody = finalViewedProfilesResp.errorBody()?.string()
                    val errorMessage = try {
                        JSONObject(errorBody ?: "view").getString("error")
                    } catch (e: JSONException) {
                        "Unknown error occurred"
                    }
                    Log.e(
                        "viewed_profiles",
                        "Registration Failed: ${finalViewedProfilesResp.code()} $errorMessage"
                    )
                }
            } catch (e: Exception) {
                Log.e("", "error: ${e.message}")
            }
        }
    }
}