package com.yszln.mvvmkt.ui.main.knowledge

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.adapter.LoadMore
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class KnowledgeArticleFragment : BaseLoadMoreFragment<HomeArticleViewModel>() {

    var mSelectCateId = 0

    companion object {
        fun newInstance(id: Int): KnowledgeArticleFragment {
            val fragment = KnowledgeArticleFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }


    var mArticleAdapter = ArticleAdapter()


    override fun loadMore(): LoadMore? = mArticleAdapter

    override fun loadMoreData() {
        mViewModel.loadHomeArticle("", mSelectCateId)
    }

    override fun refreshData() {
        mViewModel.refreshHomeArticle("", mSelectCateId)
    }

    override fun initView() {
        mSelectCateId = arguments?.getInt("id") ?: 0
        mRecyclerView.adapter = mArticleAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mViewModel.type = 3
    }

    override fun observe() {
        mViewModel.apply {
            articleList.observe(this@KnowledgeArticleFragment, Observer {

                mArticleAdapter.setNewInstance(it)
            })

        }
    }

    override fun layoutId() = R.layout.fragment_knowledge_article

}