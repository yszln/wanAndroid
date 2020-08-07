package com.yszln.lib.bean

import com.google.gson.annotations.SerializedName
import com.yszln.lib.network.ApiException

data class BaseBean<T>(
    @SerializedName("errorMsg") val message: String,
    @SerializedName("errorCode") val code: String,
    val data: T
) {
    fun data(): T {
        return if (code == "0") {
            data
        } else {
            throw ApiException(code, message)
        }
        null
    }
}
