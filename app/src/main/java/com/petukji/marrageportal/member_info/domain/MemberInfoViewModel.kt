package com.petukji.marrageportal.member_info.domain

import androidx.lifecycle.ViewModel
import com.petukji.marrageportal.bottom_nav.data.api_data.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow

class MemberInfoViewModel: ViewModel() {
    //key of the selected profile
    private val  _userProfileKey= MutableStateFlow("")
    val userProfileKey get() = _userProfileKey

    // store the selected state of the info-menus
    private val _infoMenusState = MutableStateFlow(0)
    val infoMenusState get() = _infoMenusState

    //personalDetails
    private val _userProfileData= MutableStateFlow(UserProfile())
    val userProfileData get() = _userProfileData

    fun updateUserProfileKey(key:String){
        _userProfileKey.value=key
    }

    fun updateInfoMenusState(value: Int){
        _infoMenusState.value = value
    }

}