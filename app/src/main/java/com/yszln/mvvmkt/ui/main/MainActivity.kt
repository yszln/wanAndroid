package com.yszln.mvvmkt.ui.main

import com.yszln.lib.activity.BaseActivity
import com.yszln.lib.adapter.BaseFragmentAdapter
import com.yszln.lib.fragment.BaseFragment
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.discover.fragment.DiscoverFragment
import com.yszln.mvvmkt.ui.main.home.fragment.HomeFragment
import com.yszln.mvvmkt.ui.main.mine.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var fragmentAdapter: BaseFragmentAdapter<BaseFragment>


    override fun layoutId() = R.layout.activity_main

    override fun initView() {
        StatusBarUtil.immersive(this)
        StatusBarUtil.darkMode(this)
        fragmentAdapter = BaseFragmentAdapter(supportFragmentManager);
        fragmentAdapter.addFragment(HomeFragment())
        fragmentAdapter.addFragment(DiscoverFragment())
        fragmentAdapter.addFragment(MineFragment())
        mainViewPager.adapter = fragmentAdapter
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_home -> {
                    mainViewPager.currentItem =
                        fragmentAdapter.getClassPosition(HomeFragment::class.java)
                }
                R.id.main_discover -> {
                    mainViewPager.currentItem =
                        fragmentAdapter.getClassPosition(DiscoverFragment::class.java)
                }
                R.id.main_mine -> {
                    mainViewPager.currentItem =
                        fragmentAdapter.getClassPosition(MineFragment::class.java)
                }
            }
            true
        }
    }


}