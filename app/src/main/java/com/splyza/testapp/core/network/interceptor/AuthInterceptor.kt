package com.splyza.testapp.core.network.interceptor

import com.splyza.testapp.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * This [AuthInterceptor] intercept all request and authorization the request by adding API_KEY
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest() = runBlocking {
        return@runBlocking newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
            .build()
    }
}