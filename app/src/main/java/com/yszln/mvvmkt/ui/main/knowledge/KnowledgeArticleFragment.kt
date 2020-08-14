package com.yszln.mvvmkt.ui.main.knowledge

import com.yszln.lib.adapter.LoadMore
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel

class KnowledgeArticleFragment : BaseLoadMoreFragment<HomeArticleViewModel>() {

    var mAdapter = ArticleAdapter()

    companion object {
        fun newInstance(bean: KnowLedgeItemBean): KnowledgeArticleFragment {
            val fragment = KnowledgeArticleFragment()

            return fragment
        }
    }

    override fun loadMore(): LoadMore? = mAdapter

    override fun loadMoreData() {

    }

    override fun refreshData() {
    }

    override fun initView() {

    }

    override fun observe() {

    }

    override fun layoutId() = R.layout.fragment_knowledge_article
}