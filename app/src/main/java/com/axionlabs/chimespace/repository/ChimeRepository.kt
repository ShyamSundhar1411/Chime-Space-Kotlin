package com.axionlabs.chimespace.repository

import com.axionlabs.chimespace.data.Resource
import com.axionlabs.chimespace.models.Chime
import com.axionlabs.chimespace.network.ChimesApi
import javax.inject.Inject

class ChimeRepository @Inject constructor(private val api:ChimesApi) {
    suspend fun getAllChimes(): Resource<List<Chime>>{
        return try{
            Resource.Loading(data = true)
            val chimesList = api.getAllChimes().chimes
            if(chimesList.isNotEmpty())Resource.Loading(data = false)
            Resource.Success(data = chimesList)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())

        }
    }
}