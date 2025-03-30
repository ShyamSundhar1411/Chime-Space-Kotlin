package com.axionlabs.chimespace.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import java.lang.reflect.Method

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requiresAuth = requiresAuthentication(originalRequest)
        val requestBuilder: Request.Builder =
            originalRequest.newBuilder().apply {
                header("User-Agent", "android")
                if (requiresAuth) {
                    val accessToken = SharedPreferencesManager.getValue("accessToken", "")
                    addHeader("Authorization", "Bearer $accessToken")
                }
            }
        val modifiedRequest = requestBuilder.build()
        return chain.proceed(modifiedRequest)
    }

    private fun requiresAuthentication(request: Request): Boolean {
        val method: Method? = request.tag(Invocation::class.java)?.method()
        return method?.isAnnotationPresent(RequiresAuth::class.java) ?: false
    }
}
