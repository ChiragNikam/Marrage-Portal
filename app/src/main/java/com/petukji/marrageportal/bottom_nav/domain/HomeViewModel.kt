package com.petukji.marrageportal.bottom_nav.domain

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petukji.marrageportal.bottom_nav.data.api_data.SingleUserPreference
import com.petukji.marrageportal.bottom_nav.data.api_data.UserProfile
import com.petukji.marrageportal.bottom_nav.data.api_request.Users
import com.petukji.marrageportal.bottom_nav.data.util_data.SearchFields
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // api response
    // user preference
    private val _userPreferenceData = MutableStateFlow(mutableListOf<SingleUserPreference>())
    val userPreferenceData get() = _userPreferenceData
    // user profiles
    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile get() = _userProfile

    // store the current bottom navigation route
    private val _currentDestinationBottomNav = MutableStateFlow("")
    val currentDestinationBottomNav get() = _currentDestinationBottomNav

    // to show search results on Search from Advance Search
    private val _showSearchResults = MutableStateFlow(false)
    val showSearchResults = _showSearchResults.asStateFlow()

    private val _isCurrentlyDragging = MutableStateFlow(false)
    val isCurrentlyDragging get() = _isCurrentlyDragging

    private var _searchItems = MutableStateFlow(emptyList<SearchFields>())
    val searchItems get() = _searchItems

    private var _addedSearchProperties = MutableStateFlow(mutableListOf<SearchFields>())
    val addedSearchProperties get() = _addedSearchProperties

    init {
        _searchItems.value = listOf(
            SearchFields("City", "1", Color.Gray),
            SearchFields("State", "2", Color.Blue),
            SearchFields("Country", "4", Color.Green),
            SearchFields("Gender", "5", Color.Green),
            SearchFields("Qualification", "6", Color.Green),
            SearchFields("Degree", "7", Color.Green),
            SearchFields("Age", "8", Color.Green),
            SearchFields("Religion", "9", Color.Green),
            SearchFields("Body Type", "10", Color.Green)
        )
    }

    fun startDragging() {
        _isCurrentlyDragging.value = true
    }

    fun stopDragging() {
        _isCurrentlyDragging.value = false
    }

    fun addProperty(searchFields: SearchFields) {
        _addedSearchProperties.value.add(searchFields)
    }

    fun updateCurrentDestinationBottomNav(route: String) {
        _currentDestinationBottomNav.value = route
    }

    fun updateShowSearchResultsState(showResults: Boolean) {
        _showSearchResults.value = showResults
    }

    fun loadUserPreferenceAndProfileData(profileKeyId: String) {
        viewModelScope.launch {
            val user = Users()
            // calling
            val userPreferenceResponse = async { user.service.getUserPreference() }
            val userProfileResponse = async { user.service.getUserData() }

            // waiting for user preference response
            val finalUserPreference = userPreferenceResponse.await()
            if (finalUserPreference.isSuccessful) {
                if (finalUserPreference.body() != null)
                    _userPreferenceData.value = finalUserPreference.body()!!.usersPreference.toMutableList()
            } else {
                Log.e("user_preference", finalUserPreference.errorBody().toString())
            }

            // waiting for user profile response
            val finalUserProfile = userProfileResponse.await()
            if (finalUserProfile.isSuccessful){
                if (finalUserProfile.body() != null){
                    _userProfile.value = finalUserProfile.body()!![profileKeyId]!!
                }
            } else{
                Log.e("user_profile", finalUserProfile.errorBody().toString())
            }
        }
    }
}