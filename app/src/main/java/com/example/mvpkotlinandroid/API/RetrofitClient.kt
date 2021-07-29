package com.example.mvpkotlinandroid.API

import com.example.mvpkotlinandroid.Helper.Common
import com.example.mvpkotlinandroid.Helper.StatusCodeInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getAPI() : ApiService {
            val service : ApiService

            val OkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StatusCodeInterceptor())
                .build()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(Common.BASE_URL_API)
                .client(OkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(ApiService::class.java)

            return service
        }
    }

}