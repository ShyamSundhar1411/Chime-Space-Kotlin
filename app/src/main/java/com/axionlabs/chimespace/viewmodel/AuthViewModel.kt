package com.axionlabs.chimespace.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.repository.AuthRepository
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()
    init {
        viewModelScope.launch {
            checkAuthenticationStatus()
        }
    }

    private fun checkAuthenticationStatus() {
        _isAuthenticated.value = SharedPreferencesManager.getBoolean("isAuthenticated", false)
    }

}