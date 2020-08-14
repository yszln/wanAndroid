package com.yszln.mvvmkt.ui.main.knowledge

import androidx.lifecycle.Observer
import com.yszln.lib.adapter.BaseFragmentAdapter
import com.yszln.lib.fragment.BaseFragment
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import kotlinx.android.synthetic.main.fragment_home.*

class KnowledgeFragment : BaseVMFragment<KnowledgeViewModel>() {
    override fun refreshData() {
        mViewModel.getTree()
    }

    override fun initView() {
        StatusBarUtil.setPaddingSmart(context,mTabLayout)
    }

    override fun observe() {
        mViewModel.run {
            treeList.observe(this@KnowledgeFragment, Observer {
                initViewPager(it)
            })
        }
    }

    override fun layoutId()=R.layout.fragment_knowledge

    private fun initViewPager(list: List<KnowLedgeItemBean>) {
        val fragmentAdapter = BaseFragmentAdapter<BaseFragment>(childFragmentManager)
        for (knowLedgeItemBean in list) {
            fragmentAdapter.addFragment(KnowledgeCateFragment.newInstance(knowLedgeItemBean), knowLedgeItemBean.name)
        }

        mViewPager.adapter = fragmentAdapter
        mTabLayout.setupWithViewPager(mViewPager, false)
        for (i in 0..fragmentAdapter.count) {
            mTabLayout.getTabAt(i)?.text = fragmentAdapter.getPageTitle(i)
        }



    }
}