package com.yszln.lib.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yszln.lib.R
import com.yszln.lib.viewmodel.BaseViewModel
import com.yszln.lib.viewmodel.RefreshViewModel
import java.lang.reflect.ParameterizedType

/**
 * @author: yszln
 * @date: 2020/8/7 21:13
 * @description: viewModel activity
 * @history:
 */
abstract class BaseVMActivity<VM : RefreshViewModel> : BaseActivity() ,SwipeRefreshLayout.OnRefreshListener{


    protected lateinit var mViewModel: VM

    protected var swipeRefreshLayout: SwipeRefreshLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initRefresh()
        initView()
        refreshData()
    }

    /**
     * 设置刷新头
     */
    private fun initRefresh() {
        swipeRefreshLayout=findViewById(R.id.mRefreshLayout)
        swipeRefreshLayout?.run {
                setOnRefreshListener(this@BaseVMActivity)

        }

        mViewModel.mRefreshStatus.observe(this, Observer {
            refreshEnd()
        })

    }

    /**
     * 刷新事件
     */
    override fun onRefresh() {
        refreshData()
    }

    /**
     * 刷新完毕
     */
    protected fun refreshEnd(){
        swipeRefreshLayout?.isRefreshing=false
    }



    /**
     * 请求数据
     */
    abstract fun refreshData()

    /**
     * 初始化view
     */
    abstract fun initView()

    private fun initViewModel() {
        //取第一个泛型的class
        val vmClazz =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        //创建viewModel
        mViewModel = ViewModelProvider(this).get(vmClazz)
    }
}