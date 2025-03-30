package com.axionlabs.chimespace.repository

import android.util.Log
import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.request.chime.ChimeCreateOrUpdateRequest
import com.axionlabs.chimespace.models.response.chime.ChimeCreateOrUpdateResponse
import com.axionlabs.chimespace.models.response.chime.ListChimeResponse
import com.axionlabs.chimespace.network.ChimesApi
import javax.inject.Inject

class ChimeRepository @Inject constructor(private val api:ChimesApi) {
    suspend fun getAllChimes(): DataOrException<ListChimeResponse,Boolean,Exception>{
        val dataOrException = DataOrException<ListChimeResponse,Boolean,Exception>()
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
    suspend fun createChime(chimeData: ChimeCreateOrUpdateRequest): DataOrException<ChimeCreateOrUpdateResponse,Boolean,Exception>{
        val dataOrException = DataOrException<ChimeCreateOrUpdateResponse,Boolean,Exception>()
        try{
            dataOrException.loading = true
            dataOrException.data = api.createChime(chimeData)
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        }catch(e: Exception){
            dataOrException.loading = false
            dataOrException.e  = e
        }
        Log.d("ChimeRepository", "createChime: ${dataOrException.data}")
        Log.d("ChimeRepository", "createChime: ${dataOrException.e}")
        return dataOrException
    }
}