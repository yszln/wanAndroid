package com.yszln.mvvmkt.api

import com.yszln.lib.network.RetrofitClient

object Api {
    const val BASE_API = "https://www.wanandroid.com";

    val mApiServer by lazy { RetrofitClient.createApi(BASE_API, ApiServer::class.java) }
}