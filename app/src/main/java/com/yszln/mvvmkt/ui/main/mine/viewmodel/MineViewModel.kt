package com.yszln.mvvmkt.ui.main.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.utils.SPUtils
import com.yszln.lib.utils.toJson
import com.yszln.lib.viewmodel.RefreshViewModel
import com.yszln.mvvmkt.api.Api

class MineViewModel : RefreshViewModel() {
    var isLogin = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        launch(
            block = {
                val login = Api.mApiServer.login(username, password)
                SPUtils.put("LOGIN_USER", login.data().toJson())
                isLogin.value=true
                LogUtil.e(login.toJson())
            },
            error = {
                LogUtil.e("login error->${it.message}")
            }
        )

    }

    fun loginOut(){
        launch(
            block = {
                Api.mApiServer.loginOut()
                SPUtils.put("LOGIN_USER", "")
                isLogin.value=false
            }
        )
    }



}