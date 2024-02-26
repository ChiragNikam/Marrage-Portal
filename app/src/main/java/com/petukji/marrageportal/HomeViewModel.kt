package com.petukji.marrageportal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

    // to show all the available girls/boy on Home
    private val _showGirls = MutableStateFlow(false)
    val showGirls get() = _showGirls

    // store the current bottom navigation route
    private val _currentDestinationBottomNav = MutableStateFlow("")
    val currentDestinationBottomNav get() = _currentDestinationBottomNav

    // to show search results on Search from Advance Search
    private val _showSearchResults = MutableStateFlow(false)
    val showSearchResults = _showSearchResults.asStateFlow()

    fun updateShowGirls(showGirls: Boolean){
        _showGirls.value = showGirls
    }

    fun updateCurrentDestinationBottomNav(route: String){
        _currentDestinationBottomNav.value = route
    }

    fun updateShowSearchResultsState(showResults: Boolean){
        _showSearchResults.value = showResults
    }
}