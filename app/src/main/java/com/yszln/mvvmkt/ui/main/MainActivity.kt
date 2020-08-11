package com.yszln.mvvmkt.ui.main

import androidx.navigation.Navigation
import com.yszln.lib.activity.BaseActivity
import com.yszln.lib.utils.StatusBarUtil
import com.yszln.mvvmkt.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    override fun layoutId() = R.layout.activity_main

    override fun initView() {
        StatusBarUtil.immersive(this)
        StatusBarUtil.darkMode(this)
        val findNavController = Navigation.findNavController(this, R.id.main_fragment)
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_home -> {
                    findNavController.setGraph(R.navigation.main_home)
                }
                R.id.main_discover -> {
                    findNavController.setGraph(R.navigation.main_discover)
                }
            }
            true
        }
    }


}