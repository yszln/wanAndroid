package com.yszln.mvvmkt.ui.main.home.fragment

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.youth.banner.indicator.CircleIndicator
import com.yszln.lib.adapter.BaseFragmentAdapter
import com.yszln.lib.fragment.BaseFragment
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.home.vm.HomeViewModel
import com.yszln.mvvmkt.ui.search.SearchActivity
import com.yszln.mvvmkt.widget.banner.MyBannerAdapter
import com.yszln.mvvmkt.widget.cate.CateItemBean
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author: yszln
 * @date: 2020/8/9 22:14
 * @description: 首页
 * @history:
 */
class HomeFragment : BaseVMFragment<HomeViewModel>() {


    override fun refreshData() {
        mViewModel.getBanner()


        homeCate.setData(ArrayList<CateItemBean>().apply {
            add(CateItemBean("广场"))
            add(CateItemBean("导航"))
            add(CateItemBean("体系"))
            add(CateItemBean("项目"))
            add(CateItemBean("公众号"))
            add(CateItemBean("广场"))
            add(CateItemBean("导航"))
            add(CateItemBean("体系"))
            add(CateItemBean("项目"))
            add(CateItemBean("公众号"))
            add(CateItemBean("广场"))
            add(CateItemBean("导航"))
            add(CateItemBean("体系"))
            add(CateItemBean("项目"))
            add(CateItemBean("公众号"))
        })
    }


    override fun initView() {
        StatusBarUtil.setPadding(mContext, homeTitle)

        mHomeAppBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                mRefreshLayout.isEnabled = verticalOffset == 0
            }
        })


        initBanner()

        initViewPager()

    }


    private fun initViewPager() {
        val fragmentAdapter = BaseFragmentAdapter<BaseFragment>(childFragmentManager)
        fragmentAdapter.addFragment(HomeArticleFragment.newInstance(0, 1), "首页")
        fragmentAdapter.addFragment(HomeArticleFragment.newInstance(1, 0), "置顶")
        mViewPager.adapter = fragmentAdapter
        mTabLayout.setupWithViewPager(mViewPager, false)
        for (i in 0..fragmentAdapter.count) {
            mTabLayout.getTabAt(i)?.text = fragmentAdapter.getPageTitle(i)
        }



    }

    private fun initBanner() {
        homeBanner.addBannerLifecycleObserver(this@HomeFragment)
            .indicator =
            CircleIndicator(mContext);

    }


    override fun initClick(){
        homeSearch.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)

            startActivity(
                intent, ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity as Activity, it, "search"
                )
                    .toBundle()
            );
        }
    }

    override fun observe() {
        mViewModel.apply {

            bannerList.observe(this@HomeFragment, Observer {
                homeBanner.adapter = MyBannerAdapter(it)
            })

        }


    }

    override fun layoutId() = R.layout.fragment_home

}