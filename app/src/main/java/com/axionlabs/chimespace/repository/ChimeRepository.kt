package com.axionlabs.chimespace.repository

import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.data.Resource
import com.axionlabs.chimespace.models.ListChimes
import com.axionlabs.chimespace.network.ChimesApi
import javax.inject.Inject

class ChimeRepository @Inject constructor(private val api:ChimesApi) {
    suspend fun getAllChimes(): DataOrException<ListChimes,Boolean,Exception>{
        val dataOrException = DataOrException<ListChimes,Boolean,Exception>()
        try{
            dataOrException.loading = true
            dataOrException.data = api.getAllChimes()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }catch (e:Exception){
            dataOrException.loading = false
            dataOrException.e = e
        }
        return dataOrException
    }
}