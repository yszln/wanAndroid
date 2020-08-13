package com.yszln.lib.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yszln.lib.R
import com.yszln.lib.viewmodel.RefreshViewModel
import java.lang.reflect.ParameterizedType

/**
 * @author: yszln
 * @date: 2020/8/9 21:54
 * @description: viewModel fragment
 * @history:
 */
abstract class BaseVMFragment<VM : RefreshViewModel> : BaseFragment(),
    SwipeRefreshLayout.OnRefreshListener {

    protected lateinit var mViewModel: VM

    protected var swipeRefreshLayout: SwipeRefreshLayout? = null

    private var isFirstLoad = true // 是否第一次加载

    protected lateinit var mContext: Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity!!
    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            //懒加载
            refreshData()
            isFirstLoad = false;
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRefresh()
        initView()
        observe()
        initClick()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        //view被销毁
        isFirstLoad = false
    }

    /**
     * 设置刷新头
     */
    private fun initRefresh() {
        swipeRefreshLayout = view?.findViewById(R.id.mRefreshLayout)
        swipeRefreshLayout?.run {
            setOnRefreshListener(this@BaseVMFragment)

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
    protected fun refreshEnd() {
        swipeRefreshLayout?.isRefreshing = false
    }

    private fun initViewModel() {
        //取第一个泛型的class
        val vmClazz =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        //创建viewModel
        mViewModel = ViewModelProvider(this).get(vmClazz)

    }


    /**
     * 请求数据
     */
    abstract fun refreshData()

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 点击事件
     */
    open fun initClick(){}

    /**
     * 设置观察者
     */
    abstract fun observe()


}