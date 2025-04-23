package com.axionlabs.chimespace.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.response.user.UserProfileResponse
import com.axionlabs.chimespace.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Body
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){
    private val _userData = MutableStateFlow<DataOrException<UserProfileResponse,Boolean,Exception>>(DataOrException())
    val userData = _userData.asStateFlow()
    init {
        getUserProfile()
    }
    fun getUserProfile(){
        viewModelScope.launch {
            try{
                _userData.value = DataOrException(loading = true)
                _userData.value = userRepository.getProfile()
            }catch (e:Exception){
                _userData.value = DataOrException(e = e)
            } finally{
                _userData.value = _userData.value.copy(loading = false)
            }
        }
    }
}
