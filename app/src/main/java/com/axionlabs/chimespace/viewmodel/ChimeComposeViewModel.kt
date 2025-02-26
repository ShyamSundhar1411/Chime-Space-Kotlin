package com.axionlabs.chimespace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.models.request.chime.ChimeCreateOrUpdateRequest
import com.axionlabs.chimespace.repository.ChimeRepository
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChimeComposeViewModel @Inject constructor(private val repository: ChimeRepository): ViewModel(){

    fun createChime(chimeData: ChimeCreateOrUpdateRequest){
        viewModelScope.launch {
            repository.createChime(fetchAccessTokenFromSharedPreference(),chimeData)
        }
    }
    private fun fetchAccessTokenFromSharedPreference(): String {
        return SharedPreferencesManager.getValue("accessToken", "")
    }
}