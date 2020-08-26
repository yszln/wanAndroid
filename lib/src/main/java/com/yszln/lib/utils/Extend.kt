package com.yszln.lib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
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

fun Any.toJson(): String {
    return JsonUtils.toJson(this)
}

fun Activity.start(clazz: Class<*>) {
    startActivity(Intent(this, clazz))
}

fun Fragment.start(clazz: Class<*>) {
    startActivity(Intent(this.context, clazz))
}
