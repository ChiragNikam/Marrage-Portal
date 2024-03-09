package com.petukji.marrageportal.member_info.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MemberInfoViewModel: ViewModel() {
    // store the selected state of the info-menus
    private val _infoMenusState = MutableStateFlow(0)
    val infoMenusState get() = _infoMenusState

    fun updateInfoMenusState(value: Int){
        _infoMenusState.value = value
    }
}