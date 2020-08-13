package com.yszln.mvvmkt.ui.main.mine.fragment

import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.lib.utils.toast
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.mine.viewmodel.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseVMFragment<MineViewModel>() {


    override fun refreshData() {
        mine_user_name.postDelayed({
            refreshEnd()
        }, 2000)
    }

    override fun initView() {
        StatusBarUtil.setPaddingSmart(mContext, layout)
        mine_user_name.setOnClickListener {
            LogUtil.e("mine_user_name")
            "mine_user_name".toast()

        }
    }

    override fun observe() {

    }

    override fun layoutId() = R.layout.fragment_mine
}