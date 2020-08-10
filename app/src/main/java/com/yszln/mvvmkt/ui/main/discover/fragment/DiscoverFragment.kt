package com.yszln.mvvmkt.ui.main.discover.fragment

import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.discover.vm.DiscoverViewModel

class DiscoverFragment : BaseLoadMoreFragment<DiscoverViewModel>() {


    override fun loadMoreAdapter(): LoadMoreAdapter<*> {
        val adapter = object : LoadMoreAdapter<String>(R.layout.item_rv_home_article) {
            override fun convert(holder: CommonViewHolder, item: String) {

            }
        }
        return adapter
    }

    override fun loadMoreData() {

    }

    override fun refreshData() {

    }

    override fun initView() {

    }

    override fun observe() {

    }

    override fun layoutId()=R.layout.fragment_discover
}