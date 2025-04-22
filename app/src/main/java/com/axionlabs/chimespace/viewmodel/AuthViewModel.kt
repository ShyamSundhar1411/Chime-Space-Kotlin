package com.axionlabs.chimespace.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.domain.User
import com.axionlabs.chimespace.models.request.auth.LoginRequest
import com.axionlabs.chimespace.models.request.auth.SignUpRequest
import com.axionlabs.chimespace.models.request.token.TokenRefreshRequest
import com.axionlabs.chimespace.models.response.auth.LoginResponse
import com.axionlabs.chimespace.models.response.auth.SignUpResponse
import com.axionlabs.chimespace.models.response.token.TokenRefreshResponse
import com.axionlabs.chimespace.models.response.user.UserProfileResponse
import com.axionlabs.chimespace.repository.AuthRepository
import com.axionlabs.chimespace.repository.TokenRepository
import com.axionlabs.chimespace.repository.UserRepository
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject
constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _loginData =
        MutableStateFlow<DataOrException<LoginResponse, Boolean, Exception>>(
            DataOrException(),
        )
    val loginData = _loginData.asStateFlow()

    private val _signUpData =
        MutableStateFlow<DataOrException<SignUpResponse, Boolean, Exception>>(
            DataOrException(),
        )
    private val _isAuthenticated = MutableStateFlow(false)
    private val _tokenRefreshData =
        MutableStateFlow<DataOrException<TokenRefreshResponse, Boolean, Exception>>(
            DataOrException(),
        )
    val tokenRefreshData = _tokenRefreshData.asStateFlow()
    private val _userData =
        MutableStateFlow<DataOrException<UserProfileResponse, Boolean, Exception>>(
            DataOrException()
        )
    val userData = _userData.asStateFlow()
    val isAuthenticated = _isAuthenticated.asStateFlow()
    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            checkAuthenticationStatus()
        }
    }

    val signUpData = _signUpData.asStateFlow()

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginData.value = DataOrException(loading = true)

            try {
                val response = authRepository.login(loginRequest)
                _loginData.value = response

                response.data?.let {
                    if (it.user.id.isNotEmpty()) {
                        saveAuthState(it.accessToken, it.refreshToken)
                    }
                }
            } catch (e: Exception) {
                _loginData.value = DataOrException(e = e)
                Log.e("AuthViewModel", "Login error: ${e.message}", e)
            } finally {
                _loginData.value = _loginData.value.copy(loading = false)
            }
        }
    }

    fun signUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            _signUpData.value = DataOrException(loading = true)

            try {
                val response = authRepository.signUp(signUpRequest)
                _signUpData.value = response

                response.data?.let {
                    if (it.user.id.isNotEmpty()) {
                        saveAuthState(it.accessToken, it.refreshToken)
                    }
                }
            } catch (e: Exception) {
                _signUpData.value = DataOrException(e = e)
                Log.e("AuthViewModel", "Signup error: ${e.message}", e)
            } finally {
                _signUpData.value = _signUpData.value.copy(loading = false)
            }
        }
    }

    fun logout() {
        SharedPreferencesManager.putValue("isAuthenticated", false)
    }

    private fun checkAuthenticationStatus() {
        viewModelScope.launch {
            try {
                val authenticated = SharedPreferencesManager.getValue("isAuthenticated", false)
                if (authenticated) {
                    val refreshToken = SharedPreferencesManager.getValue("refreshToken", "")
                    val tokenRefreshRequest = TokenRefreshRequest(refreshToken)
                    Log.d("RefreshRequest", tokenRefreshRequest.toString())

                    val refreshResponse = tokenRepository.refreshTokens(tokenRefreshRequest)
                    _tokenRefreshData.value = refreshResponse
                    Log.d("RefreshToken", refreshResponse.toString())

                    refreshResponse.data?.let {
                        saveAuthState(it.accessToken, it.refreshToken)
                    } ?: throw Exception("Token refresh failed")

                    val profileResponse = userRepository.getProfile()
                    _userData.value = profileResponse
                    Log.d("UserData", profileResponse.toString())

                    profileResponse.data?.let {
                        _user.value = it.profile
                    } ?: throw Exception("Fetching profile failed")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "checkAuthenticationStatus error: ${e.message}", e)
                SharedPreferencesManager.putValue("isAuthenticated", false)
                _isAuthenticated.value = false
            }
        }
    }


    private fun saveAuthState(
        accessToken: String,
        refreshToken: String,
    ) {
        SharedPreferencesManager.putValue("isAuthenticated", true)
        SharedPreferencesManager.putValue("accessToken", accessToken)
        SharedPreferencesManager.putValue("refreshToken", refreshToken)
        _isAuthenticated.value = true
    }
}
