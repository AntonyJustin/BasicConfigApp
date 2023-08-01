package com.sample.basicconfigapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel


open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    open var mContext: Context? = null


    val TAG = javaClass.simpleName

    init {
        mContext = application
    }

}