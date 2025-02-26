package com.axionlabs.chimespace.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.response.chime.ListChimeResponse
import com.axionlabs.chimespace.repository.ChimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ChimeRepository): ViewModel(){
    val data: MutableState<DataOrException<ListChimeResponse, Boolean, Exception>> = mutableStateOf(DataOrException(null,true,Exception("")))
    init {
        getAllChimes()
    }

    fun getAllChimes(){
        viewModelScope.launch {
                data.value.loading = true
                data.value = repository.getAllChimes()
                data.value.loading = false
         }
    }

}