package com.yszln.mvvmkt.ui.main.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.viewmodel.LoadMoreViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.main.mine.bean.IntegralBean

class IntegralViewModel : LoadMoreViewModel() {

    val mIntegral = MutableLiveData<IntegralBean>()
    val mIntegralList = MutableLiveData<MutableList<IntegralBean>>()

    fun getUserIntegral() {
        launch(
            block = {
                mIntegral.value = Api.mApiServer.getUserIntegral().data()
            },
            error = {

            }
        )
    }

    fun getUserIntegralInfo(userId: Int) {
        launch(
            block = {
                val data = Api.mApiServer.getUserIntegralInfo(userId, page).data().datas
                mIntegralList.value?.addAll(data)
            }
        )
    }
}