package com.yszln.mvvmkt.ui.search

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.lib.adapter.LoadMore
import com.yszln.lib.widget.input.SearchEditText
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeArticleViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseLoadMoreActivity<HomeArticleViewModel>() {

    private var mKeyWord=""

    private var mSearchAdapter: ArticleAdapter = ArticleAdapter()

    override fun refreshData() {

    }

    override fun observe() {
        mViewModel.type=2
        mViewModel.apply {
            articleList.observe(this@SearchActivity, Observer {
                mSearchAdapter.setNewInstance(it)
            })
        }
    }

    override fun layoutId() = R.layout.activity_search

    override fun initView() {
        mSearchBar.setSearchListener(object : SearchEditText.OnSearchListener {
            override fun onSearch(text: String) {
                mKeyWord=text
                if(mKeyWord.isEmpty()){
                    mSearchAdapter.clearData()
                }else{
                    mViewModel.refreshHomeArticle(text)
                }

            }
        })
        searchRecyclerView.adapter=mSearchAdapter
        searchRecyclerView.layoutManager=LinearLayoutManager(this)
    }


    override fun loadMoreData() {
        mViewModel.loadHomeArticle(mKeyWord)
    }

    override fun loadMore(): LoadMore = mSearchAdapter


}