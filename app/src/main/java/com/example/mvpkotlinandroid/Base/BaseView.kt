package com.example.mvpkotlinandroid.Base

import android.content.Context
import android.widget.Toast

interface BaseView {
    val mPresenter: BasePresenter<out BaseView>

    fun initView()

    fun showProgressDialog(){

    }

    fun dismissProgressDialog(){

    }

    fun showToast(text:String, context: Context, time: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context,text,time).show()
    }
}