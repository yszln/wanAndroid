package com.yszln.mvvmkt.ui.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.viewmodel.RefreshViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean
import com.yszln.mvvmkt.widget.cate.CateItemBean

class HomeViewModel : RefreshViewModel() {

    val bannerList = MutableLiveData<MutableList<BannerItemBean>>()
    val cates = MutableLiveData<MutableList<CateItemBean>>()


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

    fun getProjectTree() {
        launch(
            block = {
                val data = Api.mApiServer.getProjectTree().data()
                cates.value = mutableListOf<CateItemBean>().apply {
                    addAll(data)
                }
            }
        )
    }


}