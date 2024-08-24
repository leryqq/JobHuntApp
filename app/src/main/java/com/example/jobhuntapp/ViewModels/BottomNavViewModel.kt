package com.example.jobhuntapp.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomNavViewModel: ViewModel() {
    private val _selectedTab = MutableStateFlow(Tab.SEARCH)
    val selectedTab: StateFlow<Tab> = _selectedTab.asStateFlow()

    fun selectTab(tab: Tab) {
        _selectedTab.value = tab
    }
}

enum class Tab {
    AUTH, SEARCH, FAVORITE, RESPONSES, MESSAGES, PROFILE
}