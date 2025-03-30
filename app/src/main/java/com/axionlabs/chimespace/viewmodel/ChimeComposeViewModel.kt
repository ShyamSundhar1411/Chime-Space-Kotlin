package com.axionlabs.chimespace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.request.chime.ChimeCreateOrUpdateRequest
import com.axionlabs.chimespace.models.response.chime.ChimeCreateOrUpdateResponse
import com.axionlabs.chimespace.repository.ChimeRepository
import com.axionlabs.chimespace.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChimeComposeViewModel @Inject constructor(private val repository: ChimeRepository): ViewModel(){
    private val _chimeData = MutableStateFlow<DataOrException<ChimeCreateOrUpdateResponse, Boolean, Exception>>(
        DataOrException()
    )
    val chimeData = _chimeData.asStateFlow()
    fun createChime(chimeData: ChimeCreateOrUpdateRequest){
        viewModelScope.launch {
            _chimeData.value = DataOrException(loading = true)
            try{
                _chimeData.value = repository.createChime(chimeData)
            } catch (e: Exception){
                _chimeData.value = DataOrException(e = e)
            }
            finally {
                _chimeData.value = _chimeData.value.copy(loading = false)
            }
        }
    }
}