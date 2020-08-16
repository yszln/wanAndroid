package com.yszln.lib.network

import com.google.gson.Gson
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.utils.SPUtils
import com.yszln.lib.utils.toJson
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
* @author: yszln
* @date: 2020/8/16 20:58
* @description: cookie处理，
* @history:
*/
class CookieJarImpl : CookieJar {


    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        var fromJson =
            Gson().fromJson<CookieBean>(SPUtils.get("COOKIE_" + url.host()), CookieBean::class.java)
                ?: CookieBean(ArrayList<Cookie>())
        if(null==fromJson){
            fromJson= CookieBean(mutableListOf());
        }
        LogUtil.e("保存cookie->${cookies}")
        fromJson.list.addAll(cookies)
        SPUtils.put(
            "COOKIE_" + url.host(), fromJson.toJson()
        )
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        val fromJson =
            Gson().fromJson<CookieBean>(SPUtils.get("COOKIE_" + url.host()), CookieBean::class.java)
                ?: CookieBean(ArrayList<Cookie>())
        LogUtil.e("加载cookie->${fromJson.toJson()}")
        return fromJson.list
    }

    data class CookieBean(val list: MutableList<Cookie>) {
    }
}