package com.example.mvpkotlinandroid.Base

import android.content.Intent
import android.os.Bundle
import java.lang.ref.SoftReference

open class BasePresenter<T : BaseView>(v: T) {
    open var mView: SoftReference<T> = SoftReference(v)

    open fun onCreate(intent: Intent?) {
        mView.get()?.initView()
    }

    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onDestroy() {}
    open fun onCreateView(arguments: Bundle?) {}
}
