package com.axionlabs.chimespace.di

import com.axionlabs.chimespace.network.AuthApi
import com.axionlabs.chimespace.network.ChimesApi
import com.axionlabs.chimespace.utils.AuthInterceptor
import com.axionlabs.chimespace.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideChimeApi(): ChimesApi {
        val okHttpBuilder = OkHttpClient.Builder().apply{
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30,TimeUnit.SECONDS)
            writeTimeout(30,TimeUnit.SECONDS)
            addInterceptor(AuthInterceptor())
        }
        return Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChimesApi::class.java)
    }


    @Singleton
    @Provides
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}