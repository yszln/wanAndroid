package com.yszln.mvvmkt.ui.main.discover.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.discover.adapter.DiscoverAdapter
import com.yszln.mvvmkt.ui.main.discover.vm.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : BaseVMFragment<DiscoverViewModel>() {

    val mAdapter = DiscoverAdapter()


    override fun refreshData() {
        //常用网站
        mViewModel.frequentlyUsedWebsites()
    }

    override fun initView() {
        discoverRv.adapter = mAdapter
        discoverRv.layoutManager = LinearLayoutManager(mContext)
    }

    override fun observe() {
        mViewModel.apply {
            list.observe(this@DiscoverFragment, Observer {
                mAdapter.setList(it)
            })
        }
    }

    override fun layoutId() = R.layout.fragment_discover
}