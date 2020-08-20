package com.yszln.mvvmkt.ui.main.discover.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.viewmodel.RefreshViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.main.discover.bean.DiscoverItem

class DiscoverViewModel : RefreshViewModel() {

    val list = MutableLiveData<MutableList<DiscoverItem>>()

    fun frequentlyUsedWebsites() {
        launch(
            block = {
                val data = Api.mApiServer.frequentlyUsedWebsites().data()
                list.value = mutableListOf<DiscoverItem>().apply {
                    val discoverItem = DiscoverItem("常用网站", data)
                    add(discoverItem)
                }
            },
            error = {

            }
        )
    }
}