package com.axionlabs.chimespace.repository

import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.request.LoginRequest
import com.axionlabs.chimespace.models.request.SignUpRequest
import com.axionlabs.chimespace.models.response.LoginResponse
import com.axionlabs.chimespace.models.response.SignUpResponse
import com.axionlabs.chimespace.network.AuthApi
import java.util.concurrent.Executor
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: AuthApi) {
    suspend fun login(loginRequest: LoginRequest): DataOrException<LoginResponse, Boolean, Exception> {
        val dataOrException = DataOrException<LoginResponse, Boolean, Exception>()
        try{
            dataOrException.loading = true
            val response = api.login(loginRequest)
            dataOrException.data = response.body()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }catch (e: Exception){
            dataOrException.loading = false
            dataOrException.e = e
        }
        return dataOrException
    }
    suspend fun signUp(signUpRequest: SignUpRequest): DataOrException<SignUpResponse, Boolean, Exception>{
        val dataOrException = DataOrException<SignUpResponse, Boolean, Exception>()
        try{
            dataOrException.loading = true
            val response = api.signUp(signUpRequest)
            dataOrException.data = response.body()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }catch (e: Exception){
            dataOrException.loading = false
            dataOrException.e = e
        }
        return dataOrException
    }
}