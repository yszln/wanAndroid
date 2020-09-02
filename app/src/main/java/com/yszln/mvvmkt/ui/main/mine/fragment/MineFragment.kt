package com.yszln.mvvmkt.ui.main.mine.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.yszln.lib.bus.LiveDataBus
import com.yszln.lib.fragment.BaseVMFragment
import com.yszln.lib.utils.SPUtils
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.lib.utils.start
import com.yszln.lib.utils.toast
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.login.LoginActivity
import com.yszln.mvvmkt.ui.main.mine.activity.IntegralActivity
import com.yszln.mvvmkt.ui.main.mine.activity.MyArticleArticleActivity
import com.yszln.mvvmkt.ui.main.mine.activity.MyCollectionArticleActivity
import com.yszln.mvvmkt.ui.main.mine.bean.UserInfo
import com.yszln.mvvmkt.ui.main.mine.viewmodel.MineViewModel
import com.yszln.mvvmkt.utils.UserUtils
import kotlinx.android.synthetic.main.fragment_mine.*


class MineFragment : BaseVMFragment<MineViewModel>() {

    var mUserInfo: UserInfo? = null


    override fun refreshData() {
        mine_user_name.postDelayed({
            refreshEnd()
        }, 2000)
        showLoginUser()


    }

    override fun initView() {
        StatusBarUtil.setPaddingSmart(mContext, layout)

        mine_user_login.setOnClickListener {
            if (null == mUserInfo) {
                //登录
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                mViewModel.loginOut()
                //退出登录
            }
        }
    }

    override fun initClick() {

        mine_article.setOnClickListener {
            //我的文章
           if( UserUtils.checkLogin(context))
           start(MyArticleArticleActivity::class.java)
        }
        mine_collect.setOnClickListener {
            //我的收藏
            if( UserUtils.checkLogin(context))
            start(MyCollectionArticleActivity::class.java)
        }
        mine_integral.setOnClickListener {
            //我的积分
            if( UserUtils.checkLogin(context))
            start(IntegralActivity::class.java)
        }
    }

    override fun observe() {
        mViewModel.isLogin.observe(this, Observer {
            if (it) {
                //登录成功
                "登录成功".toast()
            } else {
                //退出登录
                mUserInfo = null
                "退出登录成功".toast()
                refreshData()
            }
            showLoginUser();
        })

        LiveDataBus.getChannel("login").observe(this, Observer { login->
            var isLogin=login as Boolean;
            if(isLogin){
                refreshData()
            }
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