package com.yszln.mvvmkt.ui.main.mine.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.lib.utils.SPUtils
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.login.LoginActivity
import com.yszln.mvvmkt.ui.main.mine.bean.UserInfo
import com.yszln.mvvmkt.ui.main.mine.viewmodel.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseVMFragment<MineViewModel>() {


    override fun refreshData() {
        mine_user_name.postDelayed({
            refreshEnd()
        }, 2000)
        showLoginUser()
    }

    override fun initView() {
        StatusBarUtil.setPaddingSmart(mContext, layout)
        mine_user_name.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        }
    }

    override fun observe() {
        mViewModel.isLogin.observe(this, Observer {
            showLoginUser()
        })
    }

    private fun showLoginUser() {
        try {
            val get = SPUtils.get("LOGIN_USER")
            val fromJson = Gson().fromJson(get, UserInfo::class.java)
            mine_user_name.text = fromJson.nickname
        } catch (e: Exception) {
        }
    }

    override fun layoutId() = R.layout.fragment_mine
}