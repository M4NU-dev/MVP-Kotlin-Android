package com.example.mvpkotlinandroid.UI.Main

import android.content.Context
import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.API.Models.HitsResponse
import com.example.mvpkotlinandroid.API.RetrofitClient
import com.example.mvpkotlinandroid.Helper.Common
import com.example.mvpkotlinandroid.Helper.SharedMemory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

object MainAPI: MainContract.API {
    override fun getArticles(onSuccess: (List<Article>) -> Unit, onError: (String) -> Unit){
        RetrofitClient.getAPI().getArticles().enqueue(object: Callback<HitsResponse> {
            override fun onFailure(call: Call<HitsResponse>, t: Throwable) {
                t.printStackTrace()

                onError("Ocurrio un error de conexion")
            }

            override fun onResponse(call: Call<HitsResponse>, response: Response<HitsResponse>) {
                val res : HitsResponse? = response.body()

                if(res != null) {
                    res?.let {
                        onSuccess(it.articulos)
                    }
                }else{
                    onError("Ocurrio un error inesperado")
                }
            }
        })
    }
}