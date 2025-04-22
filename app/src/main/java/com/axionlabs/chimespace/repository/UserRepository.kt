package com.axionlabs.chimespace.repository

import com.axionlabs.chimespace.data.DataOrException
import com.axionlabs.chimespace.models.response.user.UserProfileResponse
import com.axionlabs.chimespace.network.UserApi
import javax.inject.Inject

class UserRepository
    @Inject
    constructor(
        private val api: UserApi,
    ) {
        suspend fun getProfile(): DataOrException<UserProfileResponse, Boolean, Exception> {
            val dataOrException = DataOrException<UserProfileResponse, Boolean, Exception>()
            try {
                dataOrException.loading = true
                dataOrException.data = api.getProfile()
                if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
            } catch (e: Exception) {
                dataOrException.loading = false
                dataOrException.e = e
            }
            return dataOrException
        }
    }
