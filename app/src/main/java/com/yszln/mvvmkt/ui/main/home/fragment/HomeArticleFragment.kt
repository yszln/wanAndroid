package com.yszln.mvvmkt.ui.main.home.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class HomeArticleFragment : BaseLoadMoreFragment<HomeArticleViewModel>() {

    companion object {
        fun newInstance(type: Int, loadMore: Int): HomeArticleFragment {
            val homeArticleFragment = HomeArticleFragment()
            homeArticleFragment.arguments = Bundle().apply {
                putInt("type", type)
                putInt("loadMore", loadMore)
            }
            return homeArticleFragment
        }
    }

    private lateinit var mArticleAdapter: ArticleAdapter

    override fun loadMoreAdapter(): LoadMoreAdapter<*>? {
        if(mViewModel.loadMore!=1){
            return null
        }
        return mArticleAdapter
    }

    override fun loadMoreData() {
        mViewModel.loadHomeArticle()

    }

    override fun refreshData() {
        mViewModel.refreshHomeArticle()
    }

    override fun initView() {
        arguments?.run {
            getInt("type", 0)?.let {
                mViewModel.type = it
            }
            getInt("loadMore", 0)?.let {
                mViewModel.loadMore = it
            }
        }
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mArticleAdapter = ArticleAdapter()
        mRecyclerView.adapter = mArticleAdapter

    }

    override fun observe() {
        mViewModel.apply {
            articleList.observe(this@HomeArticleFragment, Observer {
                refreshEnd()
                mArticleAdapter.addData(it)
                mArticleAdapter.setNewInstance(it)
            })
        }
    }

    override fun layoutId() = R.layout.fragment_article


}