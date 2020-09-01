package com.yszln.mvvmkt.ui.main.mine.activity

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.activity.BaseLoadMoreActivity
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.mine.adapter.IntegralAdapter
import com.yszln.mvvmkt.ui.main.mine.viewmodel.IntegralViewModel
import kotlinx.android.synthetic.main.fragment_integral.*

/**
 *积分
 */
class IntegralActivity : BaseLoadMoreActivity<IntegralViewModel>() {

    val mAdapter: IntegralAdapter by lazy { IntegralAdapter() }

    var mUserId=0


    override fun refreshData() {
        mViewModel.getUserIntegral()
        mViewModel.getUserIntegralInfo()
    }

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }

    override fun observe() {
        mViewModel.apply {
            mIntegralList.observe(this@IntegralActivity, Observer {
                mAdapter.addData(it)
            })

            mIntegral.observe(this@IntegralActivity, Observer {
                integralCountTv.text = "总积分:${it.coinCount}"
                mUserId=it.userId
            })
        }

    }

    override fun layoutId() = R.layout.fragment_integral
    override fun loadMore()=mAdapter

    override fun loadMoreData() {
        mViewModel.getUserIntegralInfo()
    }
}