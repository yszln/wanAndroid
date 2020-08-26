package com.yszln.mvvmkt.ui.project

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : BaseLoadMoreActivity<HomeArticleViewModel>() {

    var cid = 0
    var title = "项目"
    val mAdapter = ArticleAdapter()


    override fun refreshData() {
        mViewModel.refreshHomeArticle("", cid)
    }

    override fun observe() {
        mViewModel.apply {
            articleList.observe(this@ProjectActivity, Observer {
                mAdapter.setNewInstance(it)
            })
        }
    }

    override fun layoutId() = R.layout.activity_project

    override fun initView() {
        mViewModel.type=4
        intent?.apply {
            cid = getIntExtra("cate", 0)
            title = getStringExtra("title") ?: "项目"
        }

        titleBar.setTitle(title)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun loadMore() = mAdapter

    override fun loadMoreData() {
        mViewModel.loadHomeArticle("", cid)
    }
}