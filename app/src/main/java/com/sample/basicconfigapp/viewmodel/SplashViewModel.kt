package com.sample.basicconfigapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.basicconfigapp.MyApplication
import com.sample.basicconfigapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val application: MyApplication) :
    BaseViewModel(application) {

    var splashlivedata: MutableLiveData<Boolean>
    var errorMessage: MutableLiveData<String?>


    init {
        errorMessage = MutableLiveData()
        splashlivedata = MutableLiveData()

    }

    fun initSplashScreen() {
        viewModelScope.launch {
            try {
                Log.e("Test", "initSplashScreen - launch - called()")
                delay(3000)
                splashlivedata.value = true
            } catch (e: Exception) {
                errorMessage.value = mContext?.getString(R.string.something_wrong)
                splashlivedata.value = false
                Log.e("Test", "Exception => " + e.message)
            }
        }
    }

}