package com.yszln.mvvmkt.ui.main.knowledge

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.adapter.LoadMore
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.lib.utils.toJson
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel
import kotlinx.android.synthetic.main.fragment_knowledge_article.*

class KnowledgeArticleFragment : BaseLoadMoreFragment<HomeArticleViewModel>() {

    var mArticleAdapter = ArticleAdapter()

    var mCateBean: KnowLedgeItemBean? = null

    companion object {
        fun newInstance(bean: KnowLedgeItemBean): KnowledgeArticleFragment {
            val fragment = KnowledgeArticleFragment()
            val bundle = Bundle()
            bundle.putString("data", bean.toJson())
            fragment.arguments = bundle
            return fragment
        }
    }

    fun setCate(knowLedgeItemBean: KnowLedgeItemBean) {
        mCateBean=knowLedgeItemBean
        onRefresh()
    }

    override fun loadMore(): LoadMore? = mArticleAdapter

    override fun loadMoreData() {
        mViewModel.loadHomeArticle("", mCateBean?.id ?: 0)
    }

    override fun refreshData() {
        mViewModel.refreshHomeArticle("", mCateBean?.id ?: 0)
    }

    override fun initView() {
//        val string = arguments?.getString("data")
//        mCateBean = Gson().fromJson(string, KnowLedgeItemBean::class.java)
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