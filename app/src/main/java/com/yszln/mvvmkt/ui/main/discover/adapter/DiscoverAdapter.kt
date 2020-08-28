package com.yszln.mvvmkt.ui.main.discover.adapter

import android.content.Intent
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yszln.lib.BaseApplication
import com.yszln.lib.adapter.CommonAdapter
import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.common.CommonWebActivity
import com.yszln.mvvmkt.ui.main.discover.bean.DiscoverItem

/**
 */
class DiscoverAdapter(layoutResId: Int = R.layout.item_rv_discover) :
    CommonAdapter<DiscoverItem>(layoutResId) {
    override fun convert(holder: CommonViewHolder, item: DiscoverItem) {
        holder.setText(R.id.discoverTitle, item.title)
        val chipGroup = holder.getView<ChipGroup>(R.id.chipGroup)
        for (item in item.items) {
            val chip = Chip(context)
            chip.text = item.name
            chipGroup.addView(chip)
            chip.setOnClickListener {
                val intent = Intent(BaseApplication.mContext, CommonWebActivity::class.java)
                intent.putExtra("url", item.link)
                BaseApplication.mContext.startActivity(intent)
            }
        }
    }
}