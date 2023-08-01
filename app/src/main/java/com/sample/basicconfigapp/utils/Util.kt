package com.sample.basicconfigapp.utils

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment

object Utils {
    fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
    fun Fragment.LogData(message:String){
        Log.d(this.javaClass.simpleName, "Log -->: "+ message)
    }
}