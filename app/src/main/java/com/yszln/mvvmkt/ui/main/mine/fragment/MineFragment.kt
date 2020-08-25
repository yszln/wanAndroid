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

    var mUserInfo:UserInfo?=null


    override fun refreshData() {
        mine_user_name.postDelayed({
            refreshEnd()
        }, 2000)
        showLoginUser()
    }

    override fun initView() {
        StatusBarUtil.setPaddingSmart(mContext, layout)

        mine_user_login.setOnClickListener {
            if(null==mUserInfo){
                //登录
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                //退出登录
                SPUtils.put("LOGIN_USER", "")
                mUserInfo=null
                showLoginUser();
            }

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
            mUserInfo = Gson().fromJson(get, UserInfo::class.java)
            mine_user_name.text = mUserInfo!!.nickname
            mine_user_login.text = "退出登陆"
        } catch (e: Exception) {
            mine_user_login.text = "登陆"
        }
    }

    override fun layoutId() = R.layout.fragment_mine
}