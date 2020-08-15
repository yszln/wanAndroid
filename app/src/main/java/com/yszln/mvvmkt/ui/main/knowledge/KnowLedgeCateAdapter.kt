package com.yszln.mvvmkt.ui.main.knowledge

import android.widget.TextView
import com.yszln.lib.adapter.CommonAdapter
import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.mvvmkt.R

class KnowLedgeCateAdapter(var firstIsAll: Boolean) :
    CommonAdapter<KnowLedgeItemBean>(R.layout.item_rv_know_cate) {
    override fun convert(holder: CommonViewHolder, item: KnowLedgeItemBean) {
        (holder.itemView as TextView).text =
            if (firstIsAll && holder.layoutPosition == 0) "全部" else item.name
    }
}