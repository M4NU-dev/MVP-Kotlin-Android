package com.example.mvpkotlinandroid.Helper

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class StatusCodeInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        Log.d(javaClass.simpleName, "Status code: " + response.code())
        return response
    }
}