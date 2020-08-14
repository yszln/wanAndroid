package com.yszln.mvvmkt.ui.main.knowledge

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.yszln.lib.adapter.BaseFragmentAdapter
import com.yszln.lib.fragment.BaseFragment
import com.yszln.lib.utils.toJson
import com.yszln.mvvmkt.R
import kotlinx.android.synthetic.main.fragment_knowledge_cate.*

class KnowledgeCateFragment : BaseFragment() {



    var mCateBean: KnowLedgeItemBean? = null




    companion object {
        fun newInstance(bean: KnowLedgeItemBean): KnowledgeCateFragment {
            val fragment = KnowledgeCateFragment()
            val bundle = Bundle()
            bundle.putString("data", bean.toJson())
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCateBean = Gson().fromJson( arguments?.getString("data")?:"", KnowLedgeItemBean::class.java)
        mCateBean?.let {
            initViewPager(it.children)
        }
    }


    private fun initViewPager(list: List<KnowLedgeItemBean>) {
        val fragmentAdapter = BaseFragmentAdapter<BaseFragment>(childFragmentManager)
        for (knowLedgeItemBean in list) {
            fragmentAdapter.addFragment(KnowledgeArticleFragment.newInstance(knowLedgeItemBean.id), knowLedgeItemBean.name)
        }

        mViewPager.adapter = fragmentAdapter
        mTabLayout.setupWithViewPager(mViewPager, false)
        for (i in 0..fragmentAdapter.count) {
            mTabLayout.getTabAt(i)?.text = fragmentAdapter.getPageTitle(i)
        }



    }




    override fun layoutId() = R.layout.fragment_knowledge_cate
}