package com.yszln.lib.network

import com.google.gson.Gson
import com.yszln.lib.utils.SPUtils
import com.yszln.lib.utils.toJson
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class LoginCookieJar : CookieJar {


    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        SPUtils.put(
            "loginCookie", CookieBean(
                cookies
            ).toJson()
        )
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        val fromJson =
            Gson().fromJson<CookieBean>(SPUtils.get("loginCookie"), CookieBean::class.java)
                ?: CookieBean(ArrayList<Cookie>())
        return fromJson.list
    }

    data class CookieBean(val list: MutableList<Cookie>) {
    }
}