package com.example.mvpkotlinandroid.UI.Main

import android.content.Context
import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.Base.BasePresenter
import java.util.ArrayList

class MainPresenter(private val view: MainContract.View, private val API: MainContract.API): MainContract.Presenter, BasePresenter<MainContract.View>(view) {
    override fun getArticles() {
        API.getArticles({
            Articles ->
                view.listArticles(Articles)
        },{
            error ->
                view.error(error)
        })
    }
}