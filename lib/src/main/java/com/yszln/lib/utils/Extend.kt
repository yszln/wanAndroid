package com.yszln.lib.utils

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yszln.lib.activity.BaseActivity


/**
 * 扩展方法
 */
fun String.isEmpty():Boolean{
    return null==this||""==this
}

fun String.toast():Unit{
    ToastUtils.showToast(this)
}

fun BaseActivity.toast(string: String){
    ToastUtils.showToast(string)
}
fun BaseViewHolder.test(){

}