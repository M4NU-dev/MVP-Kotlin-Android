package com.example.mvpkotlinandroid.Adapters.main

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.Helper.Listeners.OnClickListArticle
import com.example.mvpkotlinandroid.R

class ArticlesAdapter(val articles: List<Article>, val listener: OnClickListArticle) : RecyclerView.Adapter<ArticlesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticlesViewHolder(layoutInflater.inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articles.get(position)
        holder.bind(article, listener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}