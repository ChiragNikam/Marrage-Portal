package com.petukji.marrageportal.DataClass

import androidx.compose.ui.graphics.painter.Painter

// data class for bottom navigation
data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter
)