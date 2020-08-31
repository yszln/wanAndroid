package com.yszln.mvvmkt.ui.main.mine.adapter

import com.yszln.lib.adapter.CommonAdapter
import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.mine.bean.IntegralBean

/**
 * 积分列表适配器
 */
class IntegralAdapter : LoadMoreAdapter<IntegralBean>(R.layout.item_rv_integral) {
    override fun convert(holder: CommonViewHolder, item: IntegralBean) {

    }
}