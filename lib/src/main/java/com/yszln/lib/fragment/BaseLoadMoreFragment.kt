package com.yszln.lib.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.lib.viewmodel.LoadMoreViewModel

/**
* @author: yszln
* @date: 2020/8/9 22:01
* @description: 可以加载更多的fragment，基于BaseRecyclerViewAdapterHelper适配器
* @history:
*/
abstract class BaseLoadMoreFragment<VM : LoadMoreViewModel> : BaseVMFragment<VM>(),
    OnLoadMoreListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoadMore()

    }

    override fun onRefresh() {
        super.onRefresh()
        loadMoreAdapter()?.setList(ArrayList())
    }

    private fun initLoadMore() {
        loadMoreAdapter()?.loadMoreModule?.setOnLoadMoreListener {
            loadMoreData()
        }
        mViewModel.mLoadMoreStatus.observe(this, Observer {
            loadMoreAdapter()?.loadMoreModule?.loadMoreComplete()
        })
    }

    /**
     * 加载更多的适配器
     */
    abstract fun loadMoreAdapter(): LoadMoreAdapter<*>?


    /**
     * 加载更多监听
     */
    override fun onLoadMore() {
        loadMoreData()
    }


    /**
     * 加载更多
     */
    abstract fun loadMoreData()
}