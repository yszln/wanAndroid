package com.yszln.mvvmkt.ui.main.knowledge

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment : BaseVMFragment<KnowledgeViewModel>() {

    private var mArticleFragment: KnowledgeArticleFragment? = null

    private var mFirstCateAdapter = KnowLedgeCateAdapter(false)
    private var mSecondCateAdapter = KnowLedgeCateAdapter(true)


    override fun layoutId() = R.layout.fragment_knowledge

    override fun refreshData() {
        mViewModel.getTree()
    }

    override fun initView() {
        StatusBarUtil.setPaddingSmart(context, knowledgeTitle)
        rv1.layoutManager = LinearLayoutManager(mContext)
        rv2.layoutManager = LinearLayoutManager(mContext)
        rv1.adapter = mFirstCateAdapter
        rv2.adapter = mSecondCateAdapter

        mFirstCateAdapter.setOnItemClickListener { adapter, view, position ->
            val itemCate = mFirstCateAdapter.data[position]
            val children = mutableListOf<KnowLedgeItemBean>().apply {
                add(itemCate)
                addAll(itemCate.children)
            }

            mSecondCateAdapter.setList(children)
        }

        mSecondCateAdapter.setOnItemClickListener { adapter, view, position ->
            knowledgeTitle.text = mSecondCateAdapter.data[position].name
            mArticleFragment?.setCate(mSecondCateAdapter.data[position])
            dismissVerticalCate()
        }
    }

    override fun observe() {
        mViewModel.run {
            treeList.observe(this@KnowledgeFragment, Observer {
                setArticleFragment(it[0])
                mFirstCateAdapter.setList(it)
            })
        }
    }

    private fun setArticleFragment(cate: KnowLedgeItemBean) {
        val children = mutableListOf<KnowLedgeItemBean>()
        children.add(cate)

        children.addAll(cate.children)


        mSecondCateAdapter.setList(children)
        mArticleFragment = KnowledgeArticleFragment.newInstance(cate)
        fragmentManager?.beginTransaction()?.replace(R.id.knowledgeFragment, mArticleFragment!!)
            ?.commit()
    }


    override fun initClick() {
        knowledgeTitle.setOnClickListener {
            if (verticalCateLayout.visibility == View.VISIBLE) {
                dismissVerticalCate()
            } else {
                showVerticalCate()

            }
        }

        knowledgeMask.setOnClickListener {
            dismissVerticalCate()
        }
    }

    private fun dismissVerticalCate() {
        verticalCateLayout.visibility = View.GONE
        knowledgeMask.visibility = View.GONE
        knowledgeTitle.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.triangle_down,0)

    }

    private fun showVerticalCate() {

        verticalCateLayout.visibility = View.VISIBLE
        knowledgeMask.visibility = View.VISIBLE
        knowledgeTitle.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.triangle_up,0)
    }
}