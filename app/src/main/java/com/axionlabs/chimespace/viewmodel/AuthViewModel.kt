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
import com.axionlabs.chimespace.models.request.LoginRequest
import com.axionlabs.chimespace.models.request.SignUpRequest
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
    private val _data = MutableStateFlow(DataOrException<LoginResponse, Boolean, Exception>())
    val data = _data.asStateFlow()
    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _data.value = DataOrException(loading = true)

            try {
                val response = repository.login(loginRequest)
                _data.value = response

                response.data?.let {
                    if (it.user.id.isNotEmpty()) {
                        SharedPreferencesManager.putValue("isAuthenticated", true)
                        SharedPreferencesManager.putValue("accessToken", it.accessToken)
                        SharedPreferencesManager.putValue("refreshToken", it.refreshToken)
                    }
                }
            } catch (e: Exception) {
                _data.value = DataOrException(e = e)
                Log.e("AuthViewModel", "Login error: ${e.message}", e)
            } finally {
                _data.value = _data.value.copy(loading = false)
            }
        }
    }
    fun logout(){
        SharedPreferencesManager.putValue("isAuthenticated", false)
    }
}