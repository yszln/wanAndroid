package com.yszln.mvvmkt.ui.main.mine.activity

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel
import kotlinx.android.synthetic.main.activity_project.*

class MyArticleArticleActivity : BaseLoadMoreActivity<HomeArticleViewModel>() {

    val mAdapter = ArticleAdapter()

    override fun loadMore() = mAdapter

    override fun loadMoreData() {

    }

    override fun refreshData() {

        mViewModel.refreshHomeArticle("", 0)
    }

    override fun initView() {
        mViewModel.type = 5
        mRecyclerView.adapter=mAdapter
        mRecyclerView.layoutManager=LinearLayoutManager(this)
    }

    override fun observe() {
        mViewModel.apply {
            articleList.observe(this@MyArticleArticleActivity, Observer {
                mAdapter.setList(it)
            })
        }
    }

    override fun layoutId() = R.layout.fragment_my_article
}