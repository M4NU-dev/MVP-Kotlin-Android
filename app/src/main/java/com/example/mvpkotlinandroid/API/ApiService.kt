package com.example.mvpkotlinandroid.API

import com.example.mvpkotlinandroid.API.Models.HitsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("search_by_date?query=mobile")
    fun getArticles() : Call<HitsResponse>
}