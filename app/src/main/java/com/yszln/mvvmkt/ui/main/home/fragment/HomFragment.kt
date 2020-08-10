package com.yszln.mvvmkt.ui.main.home.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.youth.banner.indicator.CircleIndicator
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.lib.fragment.BaseLoadMoreFragment
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.adapter.ArticleAdapter
import com.yszln.mvvmkt.ui.main.home.vm.HomeViewModel
import com.yszln.mvvmkt.widget.banner.MyBannerAdapter
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
        mViewModel.getBanner()
        mViewModel.refreshHomeArticle()
    }

    override fun initView() {
        StatusBarUtil.setPadding(mContext, homeTitle)
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mArticleAdapter = ArticleAdapter()
        mRecyclerView.adapter = mArticleAdapter

        mHomeAppBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                mRefreshLayout.isEnabled = verticalOffset == 0
            }
        })


        initBanner()

    }

    private fun initBanner() {
        homeBanner.addBannerLifecycleObserver(this@HomFragment)
            .indicator =
            CircleIndicator(mContext);

    }

    override fun observe() {
        mViewModel.apply {
            articleList.observe(this@HomFragment, Observer {
                refreshEnd()
                mArticleAdapter.addData(it)
                mArticleAdapter.setNewInstance(it)
            })

            bannerList.observe(this@HomFragment, Observer {
                homeBanner.adapter = MyBannerAdapter(it)
            })

        }


    }

    override fun layoutId() = R.layout.fragment_home

    override fun loadMoreAdapter(): LoadMoreAdapter<*> {
        return mArticleAdapter
    }

    override fun loadMoreData() {
        mViewModel.loadHomeArticle()

    }
}