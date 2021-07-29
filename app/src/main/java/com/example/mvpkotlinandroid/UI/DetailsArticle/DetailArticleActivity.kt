package com.example.mvpkotlinandroid.UI.DetailsArticle


import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.Base.BaseActivity
import com.example.mvpkotlinandroid.Helper.Common
import com.example.mvpkotlinandroid.databinding.ActivityDetailArticleBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailArticleActivity : BaseActivity<ActivityDetailArticleBinding>(ActivityDetailArticleBinding::inflate), DetailsArticleContract.View {

    override val mPresenter: DetailsArticlePresenter = DetailsArticlePresenter(this, DetailArticleAPI)
    override fun setToolbar(): Boolean { return true }
    override fun setHomeAsUpEnabled(): Boolean { return true }

    override fun initView() {

        val bundle = intent.extras
        val gson   = Gson()

        val json: String? = bundle?.getString(Common.DATA_ARTICLE)

        val tempData: Article = gson.fromJson(
            json,
            object : TypeToken<Article?>() {}.type
        )

        mBinding.boxDetailArticle.wvArticle.loadUrl(tempData.story_url)
    }
}