package com.petukji.matrimonialapp.member_info.domain

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfile
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_request.Users
import com.petukji.matrimonialapp.member_info.data.api_data.LogData
import com.petukji.matrimonialapp.member_info.data.api_data.LogDataResponse
import com.petukji.matrimonialapp.member_info.data.api_data.ViewLogWriteRequest
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MemberInfoViewModel : ViewModel() {
    //key of the selected profile
    private val _userProfileKey = MutableStateFlow("")
    val userProfileKey get() = _userProfileKey

    // store the selected state of the info-menus
    private val _infoMenusState = MutableStateFlow(0)
    val infoMenusState get() = _infoMenusState

    //personalDetails
    private val _userProfileData = MutableStateFlow(UserProfile())
    val userProfileData get() = _userProfileData

    // user profiles
    private val _loggedInUserProfile = MutableStateFlow(UserProfile())
    val loggedInUserProfile get() = _loggedInUserProfile

    fun updateUserProfileKey(key: String) {
        _userProfileKey.value = key
    }

    fun updateInfoMenusState(value: Int) {
        _infoMenusState.value = value
    }

    suspend fun getSelectedUserProfile(data: UserProfileRequest) {
        viewModelScope.launch {
            val user = Users()
            val userProfileResponse = async { user.service.getSingleUserData(data) }

            val finalUserProfile = userProfileResponse.await()
            try {
                if (finalUserProfile.isSuccessful) {
                    if (finalUserProfile.body() != null) {
                        _userProfileData.value = finalUserProfile.body()!!
                    }
                    Log.d("user_profileData", _userProfileData.value.toString())
                } else {
                    Log.e("user_profileData", finalUserProfile.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.e("user_profile", e.message.toString())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun profileViewedRequest(data: UserProfileRequest) {
        viewModelScope.launch {
            val user = Users()
            try {
                // calling
                val userProfileResponse = async { user.service.getSingleUserData(data) }

                val finalUserProfile = userProfileResponse.await()
                if (finalUserProfile.isSuccessful) {
                    if (finalUserProfile.body() != null) {
                        _loggedInUserProfile.value = finalUserProfile.body()!!
                    }
                    Log.d("user_profileData", _loggedInUserProfile.value.toString())
                } else {
                    Log.e("user_profileData", finalUserProfile.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.e("user_profile", e.message.toString())
            }

            // create data and set it to the object for request
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") // get current data and time
            val current = LocalDateTime.now().format(formatter)
            val viewLogData = LogData(  // set data to the obj
                to = userProfileData.value.mobileKey,
                toName = userProfileData.value.firstName,
                toAge = userProfileData.value.age,
                toLocation = "${userProfileData.value.permanentCity} | ${userProfileData.value.permanentState}|${userProfileData.value.permanentCountry}",
                toShortDesc = userProfileData.value.longDescription,
                by = loggedInUserProfile.value.mobileKey,
                byName = loggedInUserProfile.value.firstName,
                byAge = loggedInUserProfile.value.age,
                byLocation = "${loggedInUserProfile.value.permanentCity} | ${loggedInUserProfile.value.permanentState}|${loggedInUserProfile.value.permanentCountry}",
                byShortDesc = loggedInUserProfile.value.longDescription,
                time = current.toString(),
                date = current.toString()
            )


            try {
                val connectSendResponse =
                    async { user.service.sendConnectViewLog(ViewLogWriteRequest(data = viewLogData)) }
                val finalConnectSend = connectSendResponse.await()

                finalConnectSend.enqueue(object : Callback<LogDataResponse> {
                    override fun onResponse(
                        call: Call<LogDataResponse>,
                        response: Response<LogDataResponse>
                    ) {
                        Log.d("connect_send", "profile viewed sent successfully")
                    }

                    override fun onFailure(call: Call<LogDataResponse>, t: Throwable) {
                        Log.e("connect_send", "problem sending profile: $finalConnectSend.e")

                    }
                })
//                if (finalConnectSend.isSuccessful) {
//                } else {
//                }
            } catch (e: Exception) {
                Log.e("connect_error", e.message.toString())
            }

        }
    }
}

