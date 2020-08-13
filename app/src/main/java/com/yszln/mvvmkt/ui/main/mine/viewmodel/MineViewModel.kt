package com.yszln.mvvmkt.ui.main.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.utils.toJson
import com.yszln.lib.viewmodel.RefreshViewModel
import com.yszln.mvvmkt.api.Api

class MineViewModel : RefreshViewModel() {
    private var isLogin = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        launch(
            block = {
                val login = Api.mApiServer.login("yszln", "huwei123456")
                LogUtil.e(login.toJson())
            },
            error = {
                LogUtil.e("login error->${it.message}")
            }
        )
    }

}