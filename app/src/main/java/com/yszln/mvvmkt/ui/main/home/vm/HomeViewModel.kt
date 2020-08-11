package com.yszln.mvvmkt.ui.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.viewmodel.RefreshViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean

class HomeViewModel : RefreshViewModel() {

    val bannerList = MutableLiveData<MutableList<BannerItemBean>>()


    fun getBanner() {
        launch(
            block = {
                val homeBanner = Api.mApiServer.getHomeBanner().data()
                bannerList.value = mutableListOf<BannerItemBean>().apply {
                    addAll(homeBanner)
                }
                refreshComplete()
            }
        )
    }


}