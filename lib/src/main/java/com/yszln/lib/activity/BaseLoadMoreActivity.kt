package com.yszln.lib.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.module.LoadMoreModule
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.lib.viewmodel.LoadMoreViewModel

/**
* @author: yszln
* @date: 2020/8/7 21:13
* @description: 可以加载更多的activity
* @history:
*/
abstract class BaseLoadMoreActivity<VM : LoadMoreViewModel> : BaseVMActivity<VM>(), LoadMoreModule,
    OnLoadMoreListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      initLoadMore()
    }

    override fun onRefresh() {
        super.onRefresh()
        loadMoreAdapter().setList(ArrayList())
    }

    private fun initLoadMore() {
        loadMoreAdapter().loadMoreModule.setOnLoadMoreListener {
            loadMoreData()
        }
        mViewModel.mLoadMoreStatus.observe(this, Observer {
            loadMoreAdapter().loadMoreModule.loadMoreComplete()
        })
    }

    /**
     * 加载更多的适配器
     */
    abstract fun loadMoreAdapter(): LoadMoreAdapter<*>



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