package com.axionlabs.chimespace.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.response.ListChimes
import com.axionlabs.chimespace.repository.ChimeRepository
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ChimeRepository): ViewModel(){
    val data: MutableState<DataOrException<ListChimes, Boolean, Exception>> = mutableStateOf(DataOrException(null,true,Exception("")))
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()
    init{
        viewModelScope.launch {
            getAllChimes()
            checkAuthenticationStatus()
        }
    }
    private fun getAllChimes(){
        viewModelScope.launch {
                data.value.loading = true
                data.value = repository.getAllChimes()
                data.value.loading = false
         }
    }
    private fun checkAuthenticationStatus() {
        _isAuthenticated.value = SharedPreferencesManager.getValue("isAuthenticated", false)
    }

}