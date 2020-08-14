package com.yszln.lib.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yszln.lib.R

abstract class CommonAdapter<T>(layoutResId: Int) :
    BaseQuickAdapter<T, CommonViewHolder>(layoutResId) {
    init {
        setEmptyView(R.layout.view_empty)
        isUseEmpty=true

    }


}