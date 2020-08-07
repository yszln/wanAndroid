package com.yszln.lib.viewmodel

import androidx.lifecycle.MutableLiveData

open class LoadMoreViewModel: RefreshViewModel() {

    /**
     * 加载状态
     */
    var mLoadMoreStatus=MutableLiveData<Int>()

}