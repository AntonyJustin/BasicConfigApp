package com.sample.basicconfigapp.utils.callbacks

import android.view.View

interface ClickHelper {

    fun onClick(view : View)

    fun onClick(view : View, any : Any)

    fun onClick(vararg data:Any)

}