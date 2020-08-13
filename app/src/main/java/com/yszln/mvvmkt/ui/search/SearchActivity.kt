package com.yszln.mvvmkt.ui.search

import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.lib.adapter.LoadMore
import com.yszln.mvvmkt.R

class SearchActivity : BaseLoadMoreActivity<SearchViewModel>() {

    var mSearchAdapter: SearchAdapter = SearchAdapter()

    override fun refreshData() {

    }

    override fun observe() {

    }

    override fun layoutId() = R.layout.activity_search

    override fun initView() {

    }



    override fun loadMoreData() {

    }

    override fun loadMore(): LoadMore=mSearchAdapter


}