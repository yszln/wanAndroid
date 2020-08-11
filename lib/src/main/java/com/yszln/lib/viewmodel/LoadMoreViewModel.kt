package com.yszln.lib.viewmodel

import androidx.lifecycle.MutableLiveData

open class LoadMoreViewModel : RefreshViewModel() {

    protected var page = 0

    /**
     * 加载状态
     */
    var mLoadMoreStatus = MutableLiveData<Int>()



    open fun loadMoreComplete() {
        mLoadMoreStatus.value = System.currentTimeMillis().toInt()
    }

}