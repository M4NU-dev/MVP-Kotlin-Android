package com.example.mvpkotlinandroid.Helper.Listeners

import com.example.mvpkotlinandroid.API.Models.Article

interface OnClickListArticle {
    fun onItemClick(article: Article)
}