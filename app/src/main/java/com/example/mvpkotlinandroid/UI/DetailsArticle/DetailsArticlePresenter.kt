package com.example.mvpkotlinandroid.UI.DetailsArticle

import com.example.mvpkotlinandroid.Base.BasePresenter
import com.example.mvpkotlinandroid.UI.Main.MainContract

class DetailsArticlePresenter(
    private val view: DetailsArticleContract.View,
    private val API: DetailsArticleContract.API): DetailsArticleContract.Presenter, BasePresenter<DetailsArticleContract.View>(view) {
}