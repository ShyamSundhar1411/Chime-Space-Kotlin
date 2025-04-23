package com.axionlabs.chimespace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.response.chime.ListChimeResponse
import com.axionlabs.chimespace.models.response.user.UserProfileResponse
import com.axionlabs.chimespace.repository.ChimeRepository
import com.axionlabs.chimespace.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
    private val chimeRepository: ChimeRepository
) : ViewModel() {
    private val _userData =
        MutableStateFlow<DataOrException<UserProfileResponse, Boolean, Exception>>(DataOrException())
    val userData = _userData.asStateFlow()
    private val _chimeData =
        MutableStateFlow<DataOrException<ListChimeResponse, Boolean, Exception>>(DataOrException())
    val chimeData = _chimeData.asStateFlow()


    init {
        getUserProfile()
        getChimeData()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            try {
                _userData.value = DataOrException(loading = true)
                _userData.value = userRepository.getProfile()
            } catch (e: Exception) {
                _userData.value = DataOrException(e = e)
            } finally {
                _userData.value = _userData.value.copy(loading = false)
            }
        }
    }

    fun getChimeData() {
        viewModelScope.launch {
            try {
                _chimeData.value = DataOrException(loading = true)
                _chimeData.value = chimeRepository.getChimesFromUser()
            } catch (e: Exception) {
                _chimeData.value = DataOrException(e = e)
            } finally {
                _chimeData.value = _chimeData.value.copy(loading = false)
            }
        }

    }
}
