package com.axionlabs.chimespace.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AuthViewModel() : ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()
    init {
        checkAuthenticationStatus()
    }

    private fun checkAuthenticationStatus() {
        _isAuthenticated.value = SharedPreferencesManager.getBoolean("isAuthenticated", false)
    }

}