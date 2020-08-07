package com.yszln.lib.network

import com.yszln.lib.utils.LogUtil
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

/**
* @author: yszln
* @date: 2020/8/7 21:14
* @description: OkHttp日志拦截器
* @history:
*/
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = StringBuilder()
        val startTime = System.nanoTime()

        val response = with(chain.request()) {
            //请求方式
            builder
                .append("OkHttp\n")
                .append("请求:")
                .append(method() + ":")
                //请求url
                .append(url().toString() + "\n")
                //请求头
                .append("head:${headers()}\n")

            //如果是POST请求，拼接请求的参数
            if ("POST" == method()) {
                builder.append("?")
                when (val body = body()) {
                    is FormBody -> {
                        for (i in 0 until body.size()) {
                            //拼接请求参数
                            builder.append(body.name(i) + "=" + body.value(i))
                            //除了最后一个参数，其他的参数都加上&
                            if (i < (body.size() - 1)) {
                                builder.append("&")
                            }
                        }
                    }
                }
                builder.append("\n")
            }
            //释放拦截，返回response
            chain.proceed(this)
        }

        //请求时间
        builder.append("响应时间： " + (System.nanoTime() - startTime) / 1e6 + " ms")
            //状态码
            .append("\u3000code:" + response.code() + "\n")
//            .append(response.body()?.string())


        LogUtil.e(builder.toString())

        return response
    }
}