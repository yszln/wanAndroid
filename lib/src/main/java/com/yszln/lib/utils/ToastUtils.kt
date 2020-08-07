package com.yszln.lib.utils

import android.widget.Toast
import com.yszln.lib.BaseApplication


object ToastUtils {

    private var mToast: Toast? = null
    private const val duration: Int = 2000;

    fun showToast(
        message: CharSequence
    ) {
        message?.let {
            mToast = Toast.makeText(BaseApplication.mContext, it, Toast.LENGTH_SHORT)
            mToast?.show()
        }


    }
}