package com.axionlabs.chimespace.repository

import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.request.token.TokenRefreshRequest
import com.axionlabs.chimespace.models.response.token.TokenRefreshResponse
import com.axionlabs.chimespace.network.TokenApi
import javax.inject.Inject

class TokenRepository @Inject constructor(private val api: TokenApi) {
    suspend fun refreshTokens(refreshToken: TokenRefreshRequest): DataOrException<TokenRefreshResponse, Boolean, Exception> {
        val dataOrException = DataOrException<TokenRefreshResponse, Boolean, Exception>()
        try {
            dataOrException.loading = true
            dataOrException.data = api.refreshTokens(refreshToken)
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }catch(e: Exception){
            dataOrException.loading = false
            dataOrException.e = e
        }
        return dataOrException
    }

}