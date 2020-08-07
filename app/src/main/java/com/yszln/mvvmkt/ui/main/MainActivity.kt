package com.yszln.mvvmkt.ui.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.mvvmkt.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseLoadMoreActivity<MainViewModel>() {

    private lateinit var mArticleAdapter: ArticleAdapter

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mViewModel.apply {
            articleList.observe(this@MainActivity, Observer {
                refreshEnd()
                mArticleAdapter.addData(it)
                mArticleAdapter.setNewInstance(it)
            })
        }


        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mArticleAdapter = ArticleAdapter()
        mRecyclerView.adapter = mArticleAdapter


    }

    override fun refreshData() {
        mViewModel.refreshHomeArticle()
    }

    override fun loadMoreAdapter(): LoadMoreAdapter<*> {
        return mArticleAdapter
    }

    override fun loadMoreData() {
        mViewModel.loadHomeArticle()
    }

}