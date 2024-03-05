package com.petukji.marrageportal.bottom_nav.domain

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.petukji.marrageportal.bottom_nav.data.SearchFields
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

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

    fun startDragging(){
        _isCurrentlyDragging.value = true
    }

    fun stopDragging(){
        _isCurrentlyDragging.value = false
    }

    fun addPerson(searchFields: SearchFields){
        _addedSearchProperties.value.add(searchFields)
    }

    fun updateCurrentDestinationBottomNav(route: String){
        _currentDestinationBottomNav.value = route
    }

    fun updateShowSearchResultsState(showResults: Boolean){
        _showSearchResults.value = showResults
    }
}