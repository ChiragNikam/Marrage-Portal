package com.petukji.marrageportal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.petukji.marrageportal.DataClass.PersonUiItem
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

    private var _searchItems = MutableStateFlow(emptyList<PersonUiItem>())
    val searchItems get() = _searchItems

    private var _addedSearchProperties = MutableStateFlow(mutableListOf<PersonUiItem>())
    val addedSearchProperties get() = _addedSearchProperties

    init {
        _searchItems.value = listOf(
            PersonUiItem("City", "1", Color.Gray),
            PersonUiItem("State", "2", Color.Blue),
            PersonUiItem("Country", "4", Color.Green),
            PersonUiItem("Gender", "5", Color.Green),
            PersonUiItem("Qualification", "6", Color.Green),
            PersonUiItem("Degree", "7", Color.Green),
            PersonUiItem("Age", "8", Color.Green),
            PersonUiItem("Religion", "9", Color.Green),
            PersonUiItem("Body Type", "10", Color.Green)
        )
    }

    fun startDragging(){
        _isCurrentlyDragging.value = true
    }

    fun stopDragging(){
        _isCurrentlyDragging.value = false
    }

    fun addPerson(personUiItem: PersonUiItem){
        _addedSearchProperties.value.add(personUiItem)
    }

    fun updateCurrentDestinationBottomNav(route: String){
        _currentDestinationBottomNav.value = route
    }

    fun updateShowSearchResultsState(showResults: Boolean){
        _showSearchResults.value = showResults
    }
}