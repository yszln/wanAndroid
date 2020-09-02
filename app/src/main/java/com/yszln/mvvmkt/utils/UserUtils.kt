package com.yszln.mvvmkt.utils

import android.content.Context
import com.yszln.lib.utils.SPUtils
import com.yszln.lib.utils.start
import com.yszln.lib.utils.toast
import com.yszln.mvvmkt.ui.login.LoginActivity

object UserUtils {
    public fun checkLogin(context: Context?): Boolean {
        val get = SPUtils.get("LOGIN_USER")
        if (get == null || get.isEmpty()) {
            //未登陆
            "请先登陆".toast()
            toLogin(context)
            return false
        }
        return true
    }

    private fun toLogin(context: Context?) {
        context?.start(LoginActivity::class.java)
    }
}