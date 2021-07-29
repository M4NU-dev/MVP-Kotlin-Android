package com.example.mvpkotlinandroid.UI.Main

import android.content.Context
import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.API.Models.HitsResponse
import com.example.mvpkotlinandroid.Base.BaseView
import java.util.ArrayList

interface MainContract {
    interface View: BaseView {
        fun error(message: String)
        fun listArticles(articles: List<Article>)
    }

    interface Presenter {
        fun getArticles()
    }

    interface API {
        fun getArticles(onSuccess: (List<Article>) -> Unit, onError: (String) -> Unit)
    }
}