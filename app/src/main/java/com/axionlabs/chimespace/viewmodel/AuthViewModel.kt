package com.axionlabs.chimespace.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.domain.User
import com.axionlabs.chimespace.models.response.LoginResponse
import com.axionlabs.chimespace.repository.AuthRepository
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    val data: MutableState<DataOrException<LoginResponse, Boolean, Exception>> = mutableStateOf(DataOrException(null, false, Exception("")))
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()
    init {
        viewModelScope.launch {
            checkAuthenticationStatus()
        }
    }
    fun login(username: String, password: String) {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.login(username = username, password = password)
            if(data.value.data?.user?.id?.isNotEmpty() == true){
                SharedPreferencesManager.putValue("isAuthenticated", true)
                _isAuthenticated.value = true
                SharedPreferencesManager.putValue("accessToken", data.value.data!!.accessToken)
                SharedPreferencesManager.putValue("refreshToken", data.value.data!!.refreshToken)
            }
            data.value.loading = false
            Log.d("Login", "login: ${data.value.data}")
        }
    }
    private fun checkAuthenticationStatus() {
        _isAuthenticated.value = SharedPreferencesManager.getValue("isAuthenticated", false)
    }

}