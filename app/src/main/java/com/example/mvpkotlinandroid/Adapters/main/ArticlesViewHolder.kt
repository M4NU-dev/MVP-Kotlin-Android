package com.example.mvpkotlinandroid.Adapters.main

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.Helper.Listeners.OnClickListArticle
import com.example.mvpkotlinandroid.databinding.ItemArticleBinding
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mBinding = ItemArticleBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(article: Article, listener: OnClickListArticle) {
        if(article.story_title != null) {
            if(article.story_title.isEmpty()){
                mBinding.txtTitle.setText(article.title)
            }else{
                mBinding.txtTitle.setText(article.story_title)
            }
        }else{
            if(article.title != null) {
                if(article.title.isEmpty()){
                    mBinding.txtTitle.setText("...")
                }else{
                    mBinding.txtTitle.setText(article.title)
                }
            }else{
                mBinding.txtTitle.setText("...")
            }
        }

        mBinding.txtAuthor.setText(article.author + " - " + article.getPublishedDate())

        itemView.setOnClickListener {
            listener.onItemClick(article)
        }
    }
}