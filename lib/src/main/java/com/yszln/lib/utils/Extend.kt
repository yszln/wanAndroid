package com.yszln.lib.utils

import android.content.Context
import com.chad.library.adapter.base.viewholder.BaseViewHolder


/**
 * 扩展方法
 */


fun String.toast() {
    ToastUtils.showToast(this)
}

fun Context.showToast(string: String) {
    ToastUtils.showToast(string)
}

fun BaseViewHolder.showToast(string: String) {
    ToastUtils.showToast(string)
}

fun Any.toJson():String{
  return  JsonUtils.toJson(this)
}

fun Any.For():String{
    return  JsonUtils.toJson(this)
}
