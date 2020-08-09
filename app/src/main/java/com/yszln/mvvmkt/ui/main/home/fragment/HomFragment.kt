package com.yszln.mvvmkt.ui.main.home.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
* @author: yszln
* @date: 2020/8/9 22:14
* @description: 首页
* @history:
*/
class HomFragment : BaseLoadMoreFragment<HomeViewModel>() {

    private lateinit var mArticleAdapter: ArticleAdapter


    override fun refreshData() {
        mViewModel.refreshHomeArticle()
    }

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mArticleAdapter = ArticleAdapter()
        mRecyclerView.adapter = mArticleAdapter
    }

    override fun observe() {
        mViewModel.apply {
            articleList.observe(this@HomFragment, Observer {
                refreshEnd()
                mArticleAdapter.addData(it)
                mArticleAdapter.setNewInstance(it)
            })
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun loadMoreAdapter(): LoadMoreAdapter<*> {
        return mArticleAdapter
    }

    override fun loadMoreData() {
        mViewModel.loadHomeArticle()
    }
}