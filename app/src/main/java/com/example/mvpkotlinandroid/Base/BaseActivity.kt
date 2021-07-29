package com.example.mvpkotlinandroid.Base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.mvpkotlinandroid.R

abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B): AppCompatActivity(), BaseView {

    abstract fun setToolbar(): Boolean
    abstract fun setHomeAsUpEnabled(): Boolean

    lateinit var mBinding: B
    private val mAllPresenters = HashSet<BasePresenter<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindingFactory(layoutInflater)
        setContentView(mBinding.root)

        if(setToolbar()) {
            setSupportActionBar(findViewById(R.id.toolbar))

            if (setHomeAsUpEnabled()) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }

        addPresenters()
        mAllPresenters.forEach { it.onCreate(intent) }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    open fun getPresenters():MutableList<BasePresenter<*>>{
        return mutableListOf(mPresenter)
    }

    private fun addPresenters() {
        getPresenters().forEach { mAllPresenters.add(it)}
    }

    override fun onStart() {
        super.onStart()
        mAllPresenters.forEach { it.onStart() }
    }

    override fun onResume() {
        super.onResume()
        mAllPresenters.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        mAllPresenters.forEach { it.onPause() }
    }

    override fun onStop() {
        super.onStop()
        mAllPresenters.forEach { it.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAllPresenters.forEach { it.onDestroy() }
    }

    override fun showProgressDialog() {
        super.showProgressDialog()
    }

    override fun dismissProgressDialog() {
        super.dismissProgressDialog()
    }
}
