package com.yszln.mvvmkt.ui.login

import androidx.lifecycle.Observer
import com.yszln.lib.activity.BaseVMActivity
import com.yszln.lib.utils.toast
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.main.mine.viewmodel.MineViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVMActivity<MineViewModel>() {


    override fun refreshData() {

    }

    override fun observe() {
        mViewModel.isLogin.observe(this, Observer {
            if (it) {
                //登陆成功
                finish()
            }
        })
    }

    override fun layoutId() = R.layout.activity_login

    override fun initView() {
        login.setOnClickListener {
            val usernameStr = username.text.toString().trim()
            val passwordStr = password.text.toString().trim()
            if (usernameStr.isEmpty() || passwordStr.isEmpty()) {
                "用户名或者密码格式不正确".toast()
                return@setOnClickListener
            }
            mViewModel.login(usernameStr, passwordStr)
        }

    }


}